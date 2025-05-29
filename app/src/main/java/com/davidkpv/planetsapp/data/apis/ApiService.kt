package com.davidkpv.planetsapp.data.apis

import com.davidkpv.planetsapp.data.apis.models.ApiPlanetResponse
import retrofit2.http.GET

interface ApiService {
    @GET("planets/")
    suspend fun getPlanets(): ApiPlanetResponse
}
