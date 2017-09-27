package br.com.movieapp.presentation.details

import br.com.movieapp.domain.interactor.LoadMovieDetailsUseCase
import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.presentation.details.contract.MovieDetailsActivityView
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
import br.com.movieapp.presentation.details.contract.MovieDetailsView
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by pedrohenrique on 26/09/17.
 */
class MovieDetailsPresenterImpl : MovieDetailsPresenter {

    var mActivityView: MovieDetailsActivityView
    var loadMovieDetailsUseCase: LoadMovieDetailsUseCase

    @Inject
    constructor(mActivityView: MovieDetailsActivityView, loadMovieDetailsUseCase: LoadMovieDetailsUseCase) {
        this.mActivityView = mActivityView
        this.loadMovieDetailsUseCase = loadMovieDetailsUseCase

        mActivityView.setPresenter(this)
    }

    override fun start() {

    }

    override fun loadMovieDetails(movieId: Int) {
        loadMovieDetailsUseCase.execute(object : DisposableSingleObserver<MovieDetail>(){
            override fun onSuccess(t: MovieDetail) {
                mActivityView.showMovieDetails(t)
            }

            override fun onError(e: Throwable) {

            }
        }, movieId)
    }

    override fun finish() {
        loadMovieDetailsUseCase.dispose()
    }
}