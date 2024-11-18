import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    id("org.jetbrains.kotlin.plugin.serialization")
    id("androidx.room")
    id("kotlin-parcelize")
    alias(libs.plugins.protobuf)
    alias(libs.plugins.compose.compiler)
    // id("com.autonomousapps.dependency-analysis")
}

android {
    namespace = "dev.experimental.techtrends"
    compileSdk = 35
    val p = Properties().apply {
        load(project.rootProject.file("local.properties").reader())
    }
    val baseUrl: String = p.getProperty("baseUrl")
    val appVersionCode: String = p.getProperty("appVersionCode")
    val appVersionName: String = p.getProperty("appVersionName")

    defaultConfig {
        applicationId = "dev.experimental.techtrends"
        minSdk = 26
        targetSdk = 35
        versionCode = Integer.parseInt(appVersionCode)
        versionName = appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(mapOf(Pair("room.schemaLocation", "$projectDir/schemas")))
            }
        }
        buildConfigField("String", "baseUrl", "\"${baseUrl}\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    signingConfigs {
        create("release") {
            keyAlias = "testDebugKey"
            keyPassword = "123456"
            storeFile = file("\\key\\testDebugKey.jks")
            storePassword = "123456"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }
    applicationVariants.all {
        val variant = this
        variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = if (output.outputFileName.endsWith(".aab")) {
                    "Tech Trends-${variant.baseName} - ${variant.versionName} ${variant.versionCode}.aab"
                } else {
                    "TechTrends-${variant.baseName}-${variant.versionName}.apk"
                }
                output.outputFileName = outputFileName
            }
    }
}


androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val capName = variant.name.capitalized()
            tasks.getByName<KotlinCompile>("ksp${capName}Kotlin") {
                setSource(tasks.getByName("generate${capName}Proto").outputs)
            }
        }
    }
}
protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }

}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.hilt.android)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.text.google.fonts)
    debugImplementation(libs.androidx.ui.tooling)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.dataStore)
    implementation(libs.protobuf.kotlin.lite)
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation(libs.lottie.compose)

    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01")

    implementation("androidx.compose.material:material:1.8.0-alpha05")

    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
    ksp(libs.androidx.hilt.compiler)
}