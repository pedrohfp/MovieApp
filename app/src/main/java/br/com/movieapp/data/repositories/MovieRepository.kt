package br.com.movieapp.data.repositories

import br.com.movieapp.data.repositories.contract.MovieDataSource
import br.com.movieapp.data.repositories.source.MovieRemoteDataSource

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MovieRepository constructor(val movieRemoteDataSource: MovieDataSource): MovieDataSource{

}