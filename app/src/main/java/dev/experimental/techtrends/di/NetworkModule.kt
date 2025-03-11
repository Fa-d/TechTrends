package dev.experimental.techtrends.di

import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.experimental.techtrends.BuildConfig
import dev.experimental.techtrends.api.ApiService
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun retrofitNetwork(errorInterceptor: ErrorInterceptor): ApiService {
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter).addInterceptor(errorInterceptor)
                .connectTimeout(3, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)

        }.build()

        return try {
            Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApiService::class.java)
        } catch (e: Exception) {
            throw RuntimeException("Failed to create Retrofit instance: ${e.message}", e)
        }
    }

    val BASE_URL = BuildConfig.baseUrl

    @Provides
    fun provideErrorInterceptor(): ErrorInterceptor {
        return ErrorInterceptor()
    }

}

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            chain.proceed(request)
        } catch (e: Exception) {
            Log.e("ErrorInterceptor", "Network error occurred", e)
            val code = when (e) {
                is SocketTimeoutException -> 504 // Gateway Timeout
                is UnknownHostException -> 503 // Service Unavailable
                else -> 500 // Internal Server Error
            }
            Response.Builder()
                .request(request)
               .protocol(chain.connection()?.protocol() ?: Protocol.HTTP_1_1)
                .code(code)
                .message("Custom error message: ${e.message}")
                .body(ResponseBody.create("text/plain".toMediaTypeOrNull(), ""))
                .build()
        }
    }
}