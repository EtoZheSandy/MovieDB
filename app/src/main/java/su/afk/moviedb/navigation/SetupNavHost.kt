package su.afk.moviedb.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import su.afk.moviedb.MainViewModel
import su.afk.moviedb.screens.DetailScreen
import su.afk.moviedb.screens.MainScreen
import su.afk.moviedb.screens.SplashScreen
import su.afk.moviedb.utils.Constanse

sealed class Screens(val route: String) {
    object Splash: Screens(route = Constanse.Screens.SPLASH_SCREEN)
    object Main: Screens(route = Constanse.Screens.MAIH_SCREEN)
    object Detail: Screens(route = Constanse.Screens.DETAIL_SCREEN)
}

@Composable
fun SetupNavHost(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(route = Screens.Splash.route){
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Main.route){
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = Screens.Detail.route){
            DetailScreen()
        }
    }
}