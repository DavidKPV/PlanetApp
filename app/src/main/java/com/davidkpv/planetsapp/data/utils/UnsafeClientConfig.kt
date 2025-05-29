package com.davidkpv.planetsapp.data.utils

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

fun provideUnsafeOkHttpClient(): OkHttpClient {
    val trustManager =
        @SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(
                chain: Array<X509Certificate>,
                authType: String,
            ) {}

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(
                chain: Array<X509Certificate>,
                authType: String,
            ) {}
        }

    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, arrayOf(trustManager), SecureRandom())

    return OkHttpClient
        .Builder()
        .sslSocketFactory(sslContext.socketFactory, trustManager)
        .hostnameVerifier { _, _ -> true }
        .build()
}
