package com.venture.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://fakestoreapi.com/"
private const val TIMEOUT_SECONDS = 30L

 val networkModule = module {
    single { createJson() }
    single { createLoggingInterceptor() }
    single { createOkHttpClient(get()) }
    single { createRetrofit(get(), get()) }
}

private fun createJson() = Json {
    ignoreUnknownKeys = true
    isLenient = true
    coerceInputValues = true
}

private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
    HttpLoggingInterceptor.Level.BODY
}

private fun createOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
    .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
    .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .build()

private fun createRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}