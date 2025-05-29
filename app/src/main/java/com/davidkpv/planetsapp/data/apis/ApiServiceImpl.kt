package com.davidkpv.planetsapp.data.apis

import retrofit2.Retrofit

fun provideGetPlanetApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
