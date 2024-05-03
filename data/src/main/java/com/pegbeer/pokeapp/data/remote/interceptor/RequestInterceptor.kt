package com.pegbeer.pokeapp.data.remote.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        Log.d(TAG, "intercept: $request")
        return chain.proceed(request)
    }

    companion object{
        const val TAG = "RequestInterceptor"
    }
}