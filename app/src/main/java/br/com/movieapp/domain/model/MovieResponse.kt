package br.com.movieapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 25/09/17.
 */
class MovieResponse(@SerializedName("page") var page: Int,
                    @SerializedName("total_pages") var totalPages: Int,
                    @SerializedName("results") var movieList: ArrayList<Movie>) {
    override fun toString(): String {
        return "MovieResponse(totalPages=$totalPages, movieList=$movieList)"
    }
}