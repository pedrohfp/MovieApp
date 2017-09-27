package br.com.movieapp.presentation.application.di

import dagger.Component
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 27/09/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkTestModule::class))
interface AppTestComponent: AppComponent{
}