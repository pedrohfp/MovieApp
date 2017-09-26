package br.com.movieapp.presentation.details.di

import br.com.movieapp.presentation.details.MovieDetailsActivity
import br.com.movieapp.presentation.home.MainActivity
import br.com.movieapp.presentation.home.di.MovieModule
import br.com.movieapp.presentation.utils.ActivityScoped
import dagger.Subcomponent

/**
 * Created by pedrohenrique on 26/09/17.
 */
@ActivityScoped
@Subcomponent(modules = arrayOf(MovieDetailsModule::class))
interface MovieDetailsComponent{
    fun inject(details: MovieDetailsActivity)
}