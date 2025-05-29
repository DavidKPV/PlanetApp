package com.davidkpv.planetsapp.repository

import com.davidkpv.planetsapp.data.apis.ApiService
import com.davidkpv.planetsapp.models.Planet

class PlanetRepository(
    private val apiService: ApiService,
) {
    suspend fun getPlanets(): List<Planet> =
        try {
            apiService.getPlanets().results
        } catch (e: Exception) {
            println("Error in PlanetRepository: ${e.message}")
            emptyList()
        }
}
