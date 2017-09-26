package br.com.movieapp.presentation.details

import br.com.movieapp.domain.interactor.LoadMovieDetailsUseCase
import br.com.movieapp.presentation.details.contract.MovieDetailsActivityView
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
import br.com.movieapp.presentation.details.contract.MovieDetailsView
import javax.inject.Inject

/**
 * Created by pedrohenrique on 26/09/17.
 */
class MovieDetailsPresenterImpl: MovieDetailsPresenter{

    var mActivityView: MovieDetailsActivityView
    var mMovieDetailsView: MovieDetailsView
    var loadMovieDetailsUseCase: LoadMovieDetailsUseCase

    @Inject
    constructor(mActivityView: MovieDetailsActivityView, mMovieDetailsView: MovieDetailsView, loadMovieDetailsUseCase: LoadMovieDetailsUseCase) {
        this.mActivityView = mActivityView
        this.mMovieDetailsView = mMovieDetailsView
        this.loadMovieDetailsUseCase = loadMovieDetailsUseCase

        mActivityView.setPresenter(this)
        mMovieDetailsView.setPresenter(this)
    }

    override fun start() {

    }

    override fun finish() {

    }
}