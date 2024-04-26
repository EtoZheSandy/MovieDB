package su.afk.moviedb.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import su.afk.moviedb.MainViewModel
import su.afk.moviedb.data.models.MoviesShow

@Composable
fun DetailScreen(navController: NavController, viewModel: MainViewModel, itemId: String) {

    Text(text = itemId)
}