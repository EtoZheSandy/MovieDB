package su.afk.moviedb.screens

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import su.afk.moviedb.MainViewModel
import su.afk.moviedb.data.models.MoviesShow
import java.nio.file.WatchEvent

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies
        .observeAsState(listOf()).value //передали дефф пустой лист на случай ошибки
    allMovies.forEach {
        Log.d("MyLog", it.name)
    }
    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyColumn {
            items(allMovies.take(10)){ item ->
                MoviesItem(item = item)
            }
        }
    }


}

@Composable
fun MoviesItem(item: MoviesShow) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = item.id.toString())
        Text(text = item.name)
    }
}