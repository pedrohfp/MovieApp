package br.com.movieapp.domain.interactor

import br.com.movieapp.data.repositories.MovieRepository
import br.com.movieapp.domain.interactor.base.SingleDoubleUseCase
import br.com.movieapp.domain.model.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by pedrohenrique on 25/09/17.
 */
class LoadMoviesUseCase @Inject constructor(val movieRepository: MovieRepository): SingleDoubleUseCase<MovieResponse, String, Int>(){
    override fun getSingle(params: String, secondParams: Int): Single<MovieResponse> {
        return movieRepository.loadMovies(params, secondParams)
    }


}