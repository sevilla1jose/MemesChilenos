package com.jsevilla.memeschilenos.data.network.utils

import com.jsevilla.memeschilenos.data.preferences.SecurePreferences
import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor(
    private val preferences: SecurePreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}