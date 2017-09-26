package br.com.movieapp.presentation.home

import br.com.movieapp.domain.interactor.LoadMoviesUseCase
import br.com.movieapp.presentation.home.contract.MainActivityView
import br.com.movieapp.presentation.home.contract.MovieListView
import br.com.movieapp.presentation.home.contract.MoviePresenter
import javax.inject.Inject

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MoviePresenterImpl @Inject constructor(var activityView: MainActivityView,
                         var movieListView: MovieListView, var
                         loadMoviesUseCase: LoadMoviesUseCase): MoviePresenter {

    override fun start() {

    }

    override fun finish() {
        loadMoviesUseCase.dispose()
    }

}