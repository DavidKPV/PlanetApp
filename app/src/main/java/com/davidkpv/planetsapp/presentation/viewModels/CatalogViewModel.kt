package com.davidkpv.planetsapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidkpv.planetsapp.models.Planet
import com.davidkpv.planetsapp.useCases.GetPlanetsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val getPlanetsUseCase: GetPlanetsUseCase,
) : ViewModel() {
    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets = _planets

    fun getPlanets() {
        viewModelScope.launch {
            _planets.value = getPlanetsUseCase.execute()
        }
    }
}
