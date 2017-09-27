package br.com.movieapp.presentation.details.contract

import br.com.movieapp.presentation.base.BasePresenter

/**
 * Created by pedrohenrique on 26/09/17.
 */
interface MovieDetailsPresenter : BasePresenter{
    fun loadMovieDetails(movieId: Int)
}