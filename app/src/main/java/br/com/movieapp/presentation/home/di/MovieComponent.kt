package br.com.movieapp.presentation.home.di

import br.com.movieapp.presentation.home.MainActivity
import br.com.movieapp.presentation.utils.ActivityScoped
import dagger.Subcomponent

/**
 * Created by pedrohenrique on 25/09/17.
 */
@ActivityScoped
@Subcomponent(modules = arrayOf(MovieModule::class))
interface MovieComponent{
    fun inject(main: MainActivity)
}