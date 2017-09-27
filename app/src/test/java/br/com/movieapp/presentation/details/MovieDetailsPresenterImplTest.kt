package br.com.movieapp.presentation.details

import android.support.annotation.NonNull
import br.com.movieapp.data.repositories.MovieRepository
import br.com.movieapp.domain.interactor.LoadMovieDetailsUseCase
import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.presentation.details.contract.MovieDetailsActivityView
import br.com.movieapp.presentation.details.contract.MovieDetailsPresenter
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
class MovieDetailsPresenterImplTest {

    lateinit var mActivityView: MovieDetailsActivityView
    lateinit var mLoadMovieDetailsUseCase: LoadMovieDetailsUseCase
    lateinit var mMovieRepository: MovieRepository
    lateinit var mMovieDetail: MovieDetail
    lateinit var mMovieDetailsPresenter: MovieDetailsPresenter

    @Before
    fun setUp() {

        mActivityView = mock()
        mMovieRepository = mock()
        mMovieDetail = mock()
        mLoadMovieDetailsUseCase = LoadMovieDetailsUseCase(mMovieRepository)
        mMovieDetailsPresenter = MovieDetailsPresenterImpl(mActivityView, mLoadMovieDetailsUseCase)

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
    fun testLoadMovieDetails(){
        whenever(mLoadMovieDetailsUseCase.movieRepository.loadMovieDetails(any())).thenReturn(Single.just(mMovieDetail))
        mMovieDetailsPresenter.loadMovieDetails(1)
        verify(mActivityView).showMovieDetails(mMovieDetail)
    }

}