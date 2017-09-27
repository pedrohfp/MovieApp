package br.com.movieapp.presentation.home

import android.support.annotation.NonNull
import br.com.movieapp.data.repositories.MovieRepository
import br.com.movieapp.domain.interactor.LoadMoviesUseCase
import br.com.movieapp.domain.model.MovieResponse
import br.com.movieapp.presentation.home.contract.MainActivityView
import br.com.movieapp.presentation.home.contract.MovieListView
import br.com.movieapp.presentation.home.contract.MoviePresenter
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by pedrohenrique on 27/09/17.
 */
class MoviePresenterImplTest {

    private lateinit var mActivityView: MainActivityView
    private lateinit var mMovieListView: MovieListView
    private lateinit var mLoadMoviesUseCase: LoadMoviesUseCase
    private lateinit var mMoviePresenter: MoviePresenter
    private lateinit var mMovieRepository: MovieRepository
    private lateinit var mMovieResponse: MovieResponse

    @Before
    fun setUp() {

        mActivityView = mock()
        mMovieListView = mock()
        mMovieResponse = mock()
        mMovieRepository = mock()
        mLoadMoviesUseCase = LoadMoviesUseCase(mMovieRepository)
        mMoviePresenter = MoviePresenterImpl(mActivityView, mMovieListView, mLoadMoviesUseCase)

        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun testLoadMovies(){
        whenever(mLoadMoviesUseCase.movieRepository.loadMovies(any(), any())).thenReturn(Single.just(mMovieResponse))
        mMoviePresenter.loadMovies("test", 1)
        verify(mMovieListView).showMovies(mMovieResponse)
    }
}