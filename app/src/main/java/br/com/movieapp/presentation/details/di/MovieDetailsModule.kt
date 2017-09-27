package br.com.movieapp.presentation.details.di

import br.com.movieapp.data.repositories.MovieRepository
import br.com.movieapp.data.repositories.source.MovieRemoteDataSource
import br.com.movieapp.domain.interactor.LoadMovieDetailsUseCase
import br.com.movieapp.presentation.details.MovieDetailsPresenterImpl
import br.com.movieapp.presentation.details.contract.MovieDetailsActivityView
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
import br.com.movieapp.presentation.utils.ActivityScoped
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 26/09/17.
 */
@Module
class MovieDetailsModule constructor(val activityView: MovieDetailsActivityView){
    @ActivityScoped
    @Provides
    fun provideMovieDetailsActivityView(): MovieDetailsActivityView{
        return activityView
    }

    @ActivityScoped
    @Provides
    fun provideMovieDetailsPresenter(presenter: MovieDetailsPresenterImpl): MovieDetailsPresenter{
        return presenter
    }

    @ActivityScoped
    @Provides
    fun provideLoadMovieDetailsUseCase(repository: MovieRepository): LoadMovieDetailsUseCase{
        return LoadMovieDetailsUseCase(repository)
    }

    @ActivityScoped
    @Provides
    fun provideMovieRepository(remote: MovieRemoteDataSource): MovieRepository{
        return MovieRepository(remote)
    }

    @ActivityScoped
    @Provides
    fun provideMovieRemoteDataSource(retrofit: Retrofit): MovieRemoteDataSource {
        return MovieRemoteDataSource(retrofit)
    }
}