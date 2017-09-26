package br.com.movieapp.domain.interactor

import br.com.movieapp.data.repositories.MovieRepository
import br.com.movieapp.domain.interactor.base.SingleUseCase
import br.com.movieapp.domain.model.MovieDetail
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by pedrohenrique on 26/09/17.
 */
class LoadMovieDetailsUseCase @Inject constructor(val movieRepository: MovieRepository): SingleUseCase<MovieDetail, Int>(){
    override fun getSingle(params: Int): Single<MovieDetail> {
        return movieRepository.loadMovieDetails(params)
    }
}