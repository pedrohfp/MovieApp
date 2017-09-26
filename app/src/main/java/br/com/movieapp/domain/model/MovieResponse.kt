package br.com.movieapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MovieResponse {
    @SerializedName("results")
    var movieList: ArrayList<Movie>

    constructor(movieList: ArrayList<Movie>) {
        this.movieList = movieList
    }

    override fun toString(): String {
        return "MovieResponse(movieList=$movieList)"
    }
}