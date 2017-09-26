package br.com.movieapp.data.repositories.source

import br.com.movieapp.data.repositories.contract.MovieDataSource
import br.com.movieapp.data.rest.Authenticator
import br.com.movieapp.data.rest.MovieApi
import br.com.movieapp.domain.model.MovieDetail
import br.com.movieapp.domain.model.MovieResponse
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit): MovieDataSource {

    override fun loadMovies(search: String, page: Int): Single<MovieResponse> {
        return Single.create { e ->
            try{
                val movieApi = retrofit.create(MovieApi::class.java)

                val callBody = movieApi.loadMovies(Authenticator.apiKey, search, page)

                val responseBody = callBody.execute()

                if(responseBody.isSuccessful){
                    e.onSuccess(responseBody.body()!!)
                }else{
                    e.onError(HttpException(responseBody))
                }
            }catch (error: Exception){
                error.printStackTrace()
                e.onError(error)
            }
        }
    }

    override fun loadMovieDetails(movieId: Int): Single<MovieDetail> {
        return Single.create { e ->
            try{
                val movieApi = retrofit.create(MovieApi::class.java)

                val callBody = movieApi.loadMovieDetails(Authenticator.apiKey, movieId)

                val responseBody = callBody.execute()

                if(responseBody.isSuccessful){
                    e.onSuccess(responseBody.body()!!)
                }else{
                    e.onError(HttpException(responseBody))
                }
            }catch (error: Exception){
                error.printStackTrace()
                e.onError(error)
            }
        }
    }

}