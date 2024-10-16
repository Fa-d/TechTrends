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

}

android {
    namespace = "com.faddy.techtrends"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.faddy.techtrends"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments(mapOf(Pair("room.schemaLocation", "$projectDir/schemas")))
            }
        }
        val p = Properties().apply {
            load(project.rootProject.file("local.properties").reader())
        }
        val baseUrl: String = p.getProperty("baseUrl")
        buildConfigField("String", "baseUrl", "\"${baseUrl}\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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

    implementation("com.prof18.rssparser:rssparser:6.0.8")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation(libs.lottie.compose)

}