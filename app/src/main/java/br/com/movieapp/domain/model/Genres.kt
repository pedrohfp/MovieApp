package br.com.movieapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 26/09/17.
 */
class Genres(@SerializedName("id") var id: Int, @SerializedName("name") var name: String) {
    override fun toString(): String {
        return "Genres(id=$id, name='$name')"
    }
}