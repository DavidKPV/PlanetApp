package com.davidkpv.planetsapp.useCases

import com.davidkpv.planetsapp.models.Planet
import com.davidkpv.planetsapp.repository.PlanetRepository

class GetPlanetsUseCase(
    private val repository: PlanetRepository,
) {
    suspend fun execute(): List<Planet> = repository.getPlanets()
}
