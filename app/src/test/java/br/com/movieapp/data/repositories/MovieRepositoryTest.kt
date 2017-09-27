package br.com.movieapp.data.repositories

import android.app.Application
import br.com.movieapp.BuildConfig
import br.com.movieapp.data.repositories.contract.MovieDataSource
import br.com.movieapp.data.repositories.source.MovieRemoteDataSource
import br.com.movieapp.domain.model.*
import br.com.movieapp.presentation.application.MovieApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
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

    private lateinit var mMovieRepository: MovieRepository
    private lateinit var mMovieRemoteDataSource: MovieDataSource
    private lateinit var mServer: MockWebServer


    @Before
    fun setUp() {

        mServer = MockWebServer()
        mServer.start()

        val retrofit = Retrofit.Builder()
                .baseUrl(mServer.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        mMovieRemoteDataSource = MovieRemoteDataSource(retrofit)

        mMovieRepository = MovieRepository(mMovieRemoteDataSource)
    }

    @Test
    fun testLoadMovieSuccessful(){

        val responseMovie = Movie(1, "The Avengers", "Test", "url_test")
        var response = MovieResponse(1, 20, arrayListOf(responseMovie))

        mServer.enqueue(MockResponse().setResponseCode(200).setBody(Gson().toJson(response)))

        var movieResponse: MovieResponse? = null
        var error: Throwable? = null

        mMovieRepository.loadMovies("The Avengers", 1).subscribe({
            t1: MovieResponse?, t2: Throwable? ->

            movieResponse = t1
            error = t2
        })

        assertNotNull(movieResponse)
        assertFalse(movieResponse!!.movieList.size == 0)
        assertNull(error)
    }

    @Test
    fun testLoadMovieFailed(){
        mServer.enqueue(MockResponse().setResponseCode(400))

        var movieResponse: MovieResponse? = null
        var error: Throwable? = null

        mMovieRepository.loadMovies("The Avengers", 1).subscribe({
            t1: MovieResponse?, t2: Throwable? ->

            movieResponse = t1
            error = t2
        })

        assertNull(movieResponse)
        assertNotNull(error)
    }

    @Test
    fun testLoadMovieDetailsSuccessful(){
        val genre = Genres(1, "Test")
        val companies = ProductionCompanies("test", 2)
        val response = MovieDetail(false, "test", arrayListOf(genre), "", 1, "test", "Test", "Test", 0.0, "test", arrayListOf(companies), "test", "test", "test", "test", 0.0f)

        mServer.enqueue(MockResponse().setResponseCode(200).setBody(Gson().toJson(response)))

        var movieDetail: MovieDetail? = null
        var error: Throwable? = null

        mMovieRepository.loadMovieDetails(1).subscribe({
            t1: MovieDetail?, t2: Throwable? ->

            movieDetail = t1
            error = t2
        })

        assertNotNull(movieDetail)
        assertNull(error)
    }

    @Test
    fun testLoadMovieDetailsFailed(){
        mServer.enqueue(MockResponse().setResponseCode(400))

        var movieDetail: MovieDetail? = null
        var error: Throwable? = null

        mMovieRepository.loadMovieDetails(1).subscribe({
            t1: MovieDetail?, t2: Throwable? ->

            movieDetail = t1
            error = t2
        })

        assertNull(movieDetail)
        assertNotNull(error)
    }

}