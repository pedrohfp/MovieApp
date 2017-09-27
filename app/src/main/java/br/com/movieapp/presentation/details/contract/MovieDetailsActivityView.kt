package br.com.movieapp.presentation.details.contract

import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.presentation.base.BaseActivity

/**
 * Created by pedrohenrique on 26/09/17.
 */
abstract class MovieDetailsActivityView : BaseActivity<MovieDetailsPresenter>(){
    abstract fun showMovieDetails(details: MovieDetail)
}