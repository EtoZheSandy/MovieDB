package su.afk.moviedb.data.retrofit

import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    suspend fun getAllMovie() = movieService.getAllMovies()
}