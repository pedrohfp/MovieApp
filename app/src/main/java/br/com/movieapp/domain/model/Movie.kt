package br.com.movieapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 25/09/17.
 */
class Movie {
    @SerializedName("id")
    var id: Int
    @SerializedName("title")
    var title: String
    @SerializedName("overview")
    var description: String
    @SerializedName("poster_path")
    var posterPath: String

    constructor(id: Int, name: String, description: String, posterPath: String) {
        this.id = id
        this.title = name
        this.description = description
        this.posterPath = posterPath
    }

    override fun toString(): String {
        return "Movie(id=$id, name='$title', " +
                "description='$description', " +
                "posterPath='$posterPath')"
    }


}