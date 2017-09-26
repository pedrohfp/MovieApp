package br.com.movieapp.presentation.base

/**
 * Created by pedrohenrique on 25/09/17.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}
