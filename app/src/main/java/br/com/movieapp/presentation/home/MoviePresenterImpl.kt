package br.com.movieapp.presentation.home

import br.com.movieapp.domain.interactor.LoadMoviesUseCase
import br.com.movieapp.domain.interactor.base.SingleUseCase
import br.com.movieapp.domain.model.MovieResponse
import br.com.movieapp.presentation.home.contract.MainActivityView
import br.com.movieapp.presentation.home.contract.MovieListView
import br.com.movieapp.presentation.home.contract.MoviePresenter
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MoviePresenterImpl: MoviePresenter {

    private var mActivityView: MainActivityView
    private var mMovieListView: MovieListView
    private var mLoadMoviesUseCase: LoadMoviesUseCase

    @Inject
    constructor(mActivityView: MainActivityView, mMovieListView: MovieListView, mLoadMoviesUseCase: LoadMoviesUseCase) {
        this.mActivityView = mActivityView
        this.mMovieListView = mMovieListView
        this.mLoadMoviesUseCase = mLoadMoviesUseCase

        mActivityView.setPresenter(this)
        mMovieListView.setPresenter(this)
    }


    override fun start() {

    }

    override fun loadMovies(search: String, page: Int) {
        mLoadMoviesUseCase.execute(object: DisposableSingleObserver<MovieResponse>(){
            override fun onSuccess(t: MovieResponse) {
                mMovieListView.showMovies(t)
            }
            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }, search, page)
    }

    override fun finish() {
        mLoadMoviesUseCase.dispose()
    }

}