package uz.suhrob.api

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "6cab96353248418eb47d386f88b7b427"

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()
        val url = originalUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}