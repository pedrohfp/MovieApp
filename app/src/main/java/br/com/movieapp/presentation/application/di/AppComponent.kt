package br.com.movieapp.presentation.application.di

import br.com.movieapp.presentation.details.di.MovieDetailsComponent
import br.com.movieapp.presentation.details.di.MovieDetailsModule
import br.com.movieapp.presentation.home.di.MovieComponent
import br.com.movieapp.presentation.home.di.MovieModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 25/09/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun plusMovie(module: MovieModule): MovieComponent
    fun plusDetailsMovie(module: MovieDetailsModule): MovieDetailsComponent
}