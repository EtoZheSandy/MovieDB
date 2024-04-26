package su.afk.moviedb.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import su.afk.moviedb.MainViewModel
import su.afk.moviedb.data.models.MoviesShow
import su.afk.moviedb.utils.HtmlText

@Composable
fun DetailScreen(viewModel: MainViewModel, itemId: String) {
    // Получаем listItem и выбираем элемент у которого id == с тем что передали в конструктор
    // Из массива достали наш current элемент
    val currentItem = viewModel.allMovies.observeAsState(listOf()).value
        .firstOrNull{ it.id == itemId.toInt()}

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 8.dp)
    ) {
        LazyColumn {
            item{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = currentItem?.image?.original,
                        contentDescription = "",
                        modifier = Modifier.size(512.dp)
                    )
                    Text(
                        text = currentItem?.name ?: "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 32.dp)
                    )

                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Рейтинг: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = currentItem?.rating?.average.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Row(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Жанр: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        currentItem?.genres?.take(3)?.forEach {
                            Text(
                                text = "$it, ",
                                fontSize = 18.sp
                            )
                        }
                    }

                    HtmlText(
                        html = currentItem?.summary ?: "",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            }
        }
}