package com.davidkpv.planetsapp.navigation

sealed class NavigationScreens(
    val route: String,
) {
    data object SplashScreen : NavigationScreens("splash_screen")

    data object RegisterScreen : NavigationScreens("register_screen")

    data object CatalogScreen : NavigationScreens("catalog_screen")

    data object DetailScreen : NavigationScreens("detail_screen")
}
