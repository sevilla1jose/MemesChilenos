package com.jsevilla.memeschilenos.data.network.remote

import com.jsevilla.memeschilenos.data.BuildConfig
import com.jsevilla.memeschilenos.data.network.utils.SupportInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val MAX_TIMEOUT = 60

    fun create(authInterceptor: SupportInterceptor, baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout(MAX_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.readTimeout(MAX_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(MAX_TIMEOUT.toLong(), TimeUnit.SECONDS)

        builder.addInterceptor(interceptor())
            .addInterceptor(authInterceptor)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(builder.build())
            .build()
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }
}