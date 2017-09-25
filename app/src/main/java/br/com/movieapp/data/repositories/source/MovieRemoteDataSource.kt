package br.com.movieapp.data.repositories.source

import br.com.movieapp.data.repositories.contract.MovieDataSource
import retrofit2.Retrofit

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MovieRemoteDataSource constructor(val retrofit: Retrofit): MovieDataSource {

}