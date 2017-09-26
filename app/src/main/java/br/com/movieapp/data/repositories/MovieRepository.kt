package br.com.movieapp.data.repositories

import br.com.movieapp.data.repositories.contract.MovieDataSource
import br.com.movieapp.data.repositories.source.MovieRemoteDataSource
import br.com.movieapp.domain.model.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MovieRepository @Inject constructor(private val movieRemoteDataSource: MovieDataSource): MovieDataSource{
    override fun loadMovies(search: String): Single<MovieResponse> {
        return movieRemoteDataSource.loadMovies(search)
    }
}