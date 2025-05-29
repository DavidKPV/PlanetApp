package com.davidkpv.planetsapp.data.apis

import com.davidkpv.planetsapp.data.utils.BASE_URL
import com.davidkpv.planetsapp.data.utils.provideUnsafeOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideUnsafeOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
