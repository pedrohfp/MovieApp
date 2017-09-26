package br.com.movieapp.data.repositories

import android.app.Application
import br.com.movieapp.BuildConfig
import br.com.movieapp.data.repositories.contract.MovieDataSource
import br.com.movieapp.data.repositories.source.MovieRemoteDataSource
import br.com.movieapp.domain.model.MovieResponse
import br.com.movieapp.presentation.application.MovieApplication
import com.google.gson.GsonBuilder
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pedrohenrique on 25/09/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(25))
class MovieRepositoryTest {

    lateinit var mMovieRepository: MovieRepository
    lateinit var mMovieRemoteDataSource: MovieDataSource


    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
                .baseUrl(MovieApplication.mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        mMovieRemoteDataSource = MovieRemoteDataSource(retrofit)

        mMovieRepository = MovieRepository(mMovieRemoteDataSource)
    }

    @Test
    fun testLoadMovieSuccessful(){

        var movieResponse: MovieResponse? = null
        var error: Throwable? = null

        mMovieRepository.loadMovies("The Avengers", 0).subscribe({
            t1: MovieResponse?, t2: Throwable? ->

            movieResponse = t1
            error = t2
        })

        assertNotNull(movieResponse)
        assertFalse(movieResponse!!.movieList.size == 0)
        assertNull(error)
    }
}