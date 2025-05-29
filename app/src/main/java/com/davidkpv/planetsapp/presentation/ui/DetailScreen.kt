package com.davidkpv.planetsapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.davidkpv.planetsapp.models.Planet
import com.davidkpv.planetsapp.utils.MEDIUM_PADDING

@Composable
fun DetailScreen(
    appNavContext: NavController,
    modifier: Modifier,
    planetUrl: String,
) {
    DetailScreenUi(
        modifier = modifier,
        planet = Planet("Tierra", "", "", "", "", "", "", "", "", emptyList(), emptyList(), "", "", ""),
    )
}

@Composable
fun DetailScreenUi(
    modifier: Modifier,
    planet: Planet,
) {
    Column(modifier = modifier.fillMaxSize().padding(MEDIUM_PADDING)) {
        Text(planet.name, fontWeight = FontWeight.Bold)
        Text("Clima: ${planet.climate}")
        Text("Terreno: ${planet.terrain}")
        Text("Población: ${planet.population}")
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailScreenPreview() {
    DetailScreen(
        rememberNavController(),
        Modifier,
        "dummyPlanet",
    )
}
