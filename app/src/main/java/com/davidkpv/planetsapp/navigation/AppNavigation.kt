package com.davidkpv.planetsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.davidkpv.planetsapp.presentation.ui.CatalogScreen
import com.davidkpv.planetsapp.presentation.ui.DetailScreen
import com.davidkpv.planetsapp.presentation.ui.RegisterScreen
import com.davidkpv.planetsapp.presentation.ui.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationScreens.SplashScreen.route,
    ) {
        composable(NavigationScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(NavigationScreens.RegisterScreen.route) {
            RegisterScreen(navController, modifier)
        }
        composable(NavigationScreens.CatalogScreen.route) {
            CatalogScreen(navController, modifier)
        }
        composable(
            route = "${NavigationScreens.DetailScreen.route}/{planet}",
            arguments =
                listOf(
                    navArgument("planet") {
                        type = NavType.StringType
                    },
                ),
        ) { argument ->
            DetailScreen(
                navController,
                modifier,
                planetUrl = argument.arguments?.getString("planet").orEmpty(),
            )
        }
    }
}
