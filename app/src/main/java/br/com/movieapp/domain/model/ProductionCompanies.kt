package br.com.movieapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 26/09/17.
 */
class ProductionCompanies(@SerializedName("name") var name: String, @SerializedName("id") var id: Int) {
    override fun toString(): String {
        return "ProductionCompanies(name='$name', id=$id)"
    }
}