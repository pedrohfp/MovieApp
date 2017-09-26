package br.com.movieapp.data.rest

import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.domain.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by pedrohenrique on 25/09/17.
 */
interface MovieApi{
    @GET("search/movie")
    fun loadMovies(@Query("api_key") apiKey: String, @Query("query") search: String, @Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun loadMovieDetails(@Query("api_key") apiKey: String, @Path("movie_id") movieId: Int): Call<MovieDetail>
}