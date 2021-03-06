package br.com.movieapp.presentation.home.contract

import br.com.movieapp.domain.model.MovieResponse
import br.com.movieapp.presentation.base.BaseView

/**
 * Created by pedrohenrique on 25/09/17.
 */
interface MovieListView: BaseView<MoviePresenter>{
    fun showMovies(movieResponse: MovieResponse)
}