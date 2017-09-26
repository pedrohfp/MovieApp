package br.com.movieapp.data.repositories.contract

import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.domain.model.MovieResponse
import io.reactivex.Single

/**
 * Created by pedrohenrique on 25/09/17.
 */
interface MovieDataSource{
    fun loadMovies(search: String, page: Int): Single<MovieResponse>
    fun loadMovieDetails(movieId: Int): Single<MovieDetail>
}