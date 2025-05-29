package com.davidkpv.planetsapp.data.apis.models

import com.davidkpv.planetsapp.models.Planet

data class ApiPlanetResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Planet>
)
