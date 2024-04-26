package su.afk.moviedb.data.retrofit

import retrofit2.Response
import retrofit2.http.GET
import su.afk.moviedb.data.models.MoviesShow

interface MovieService {

    @GET("/shows")
    suspend fun getAllMovies() : Response<List<MoviesShow>>
}