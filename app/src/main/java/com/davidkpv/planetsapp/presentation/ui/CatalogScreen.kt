package com.davidkpv.planetsapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.davidkpv.planetsapp.R
import com.davidkpv.planetsapp.models.Planet
import com.davidkpv.planetsapp.navigation.NavigationScreens
import com.davidkpv.planetsapp.presentation.viewModels.CatalogViewModel
import com.davidkpv.planetsapp.utils.LARGE_IMAGE
import com.davidkpv.planetsapp.utils.MEDIUM_PADDING
import com.davidkpv.planetsapp.utils.PLANET_IMAGE
import com.davidkpv.planetsapp.utils.SIMPLE_PADDING
import com.davidkpv.planetsapp.utils.SMALL_PADDING
import com.davidkpv.planetsapp.utils.toB64
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogScreen(
    appNavController: NavController,
    modifier: Modifier,
    viewModel: CatalogViewModel = koinViewModel(),
) {
    val planets by viewModel.planets.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPlanets()
    }

    CatalogScreenUi(
        modifier = modifier,
        cardAction = { planet ->
            appNavController.navigate("${NavigationScreens.DetailScreen.route}/${planet.url.toB64()}")
        },
        planetsList = planets,
    )
}

@Composable
fun CatalogScreenUi(
    modifier: Modifier,
    cardAction: (planet: Planet) -> Unit,
    planetsList: List<Planet>,
) {
    LazyColumn(
        modifier = modifier.padding(MEDIUM_PADDING),
    ) {
        items(planetsList) { planet ->
            Card(modifier = Modifier.padding(SIMPLE_PADDING).clickable { cardAction(planet) }) {
                Row(modifier = Modifier.padding(SMALL_PADDING)) {
                    Text(text = planet.name, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    AsyncImage(
                        model =
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(PLANET_IMAGE)
                                .crossfade(true)
                                .placeholder(R.drawable.ic_launcher_background)
                                .error(R.drawable.ic_launcher_background)
                                .build(),
                        contentScale = ContentScale.Fit,
                        contentDescription = "",
                        modifier = Modifier.size(LARGE_IMAGE).weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CatalogScreenPreview() {
    CatalogScreen(
        rememberNavController(),
        Modifier,
    )
}
