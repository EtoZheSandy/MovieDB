package su.afk.moviedb.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import su.afk.moviedb.MainViewModel
import su.afk.moviedb.data.models.MoviesShow
import su.afk.moviedb.navigation.Screens
import java.nio.file.WatchEvent

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies
        .observeAsState(listOf()).value //передали дефф пустой лист на случай ошибки

    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(6.dp)
        ) {
            items(allMovies.take(10)){ item ->
                MoviesItem(item = item, navController = navController)
            }
        }
    }


}

@Composable
fun MoviesItem(item: MoviesShow, navController:NavController) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable {
                       navController.navigate(Screens.Detail.route + "/${item.id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            AsyncImage(
                model = item.image.medium,
                contentDescription = "",
                modifier = Modifier.size(120.dp)
            )

            Column(
            ) {
                Text(
                    text = item.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Row {
                    Text(
                        text = "Рейтинг: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = item.rating.average.toString())
                }

                Row {
                    Text(
                        text = "Жанр: ",
                        fontWeight = FontWeight.Bold
                    )
                    item.genres.take(2).forEach { 
                        Text(text = " $it ")
                    }
                }

                Row {
                    Text(
                        text = "Премьера: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = item.premiered)
                }

            }

        }
    }
}