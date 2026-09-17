package com.rafaelcosta.carteirinhadigital2devest_b.app.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.data.remote.service.AuthApi
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkFactory {
    private const val BASE_URL ="http://10.0.2.2:8080/"
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun createRetrofit(
        baseUrl: String = BASE_URL
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json"
                    .toMediaType()
                )
            )
            .build()
    }

    fun createUnidadeCurricularApi(
        baseUrl: String = BASE_URL
    ): UnidadeCurricularApi {
        return createRetrofit(baseUrl)
            .create(UnidadeCurricularApi::class.java)
    }
    fun createLoginApi(
        baseUrl: String = BASE_URL
    ): AuthApi {
        return createRetrofit(baseUrl)
            .create(AuthApi::class.java)
    }


}