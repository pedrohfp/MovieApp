package br.com.movieapp.presentation.application.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 25/09/17.
 */
@Module
class AppModule constructor(val application: Application){
    @Provides
    @Singleton
    fun provideApplication(): Application{
        return application
    }
}