package br.com.movieapp.presentation.home.di

import br.com.movieapp.data.repositories.MovieRepository
import br.com.movieapp.data.repositories.source.MovieRemoteDataSource
import br.com.movieapp.domain.interactor.LoadMoviesUseCase
import br.com.movieapp.presentation.home.MoviePresenterImpl
import br.com.movieapp.presentation.home.contract.MainActivityView
import br.com.movieapp.presentation.home.contract.MovieListView
import br.com.movieapp.presentation.home.contract.MoviePresenter
import br.com.movieapp.presentation.utils.ActivityScoped
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 25/09/17.
 */
@Module
class MovieModule constructor(val activityView: MainActivityView, val movieListView: MovieListView){
    @ActivityScoped
    @Provides
    fun provideMainActivityView(): MainActivityView{
        return activityView
    }

    @ActivityScoped
    @Provides
    fun provideMovieListView(): MovieListView{
        return movieListView
    }

    @ActivityScoped
    @Provides
    fun provideMoviePresenter(presenter: MoviePresenterImpl): MoviePresenter{
        return presenter
    }

    @ActivityScoped
    @Provides
    fun provideLoadMoviesUseCase(repository: MovieRepository): LoadMoviesUseCase{
        return LoadMoviesUseCase(repository)
    }

    @ActivityScoped
    @Provides
    fun provideMovieRepository(remote: MovieRemoteDataSource): MovieRepository{
        return MovieRepository(remote)
    }

    @ActivityScoped
    @Provides
    fun provideMovieRemoteDataSource(retrofit: Retrofit): MovieRemoteDataSource{
        return MovieRemoteDataSource(retrofit)
    }
}