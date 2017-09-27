package br.com.movieapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pedrohenrique on 26/09/17.
 */
class MovieDetail(@SerializedName("adult") var adult: Boolean?,
                  @SerializedName("backdrop_path") var backdropPath: String?,
                  @SerializedName("genres") var genresList: ArrayList<Genres>?,
                  @SerializedName("homepage") var homepage: String?,
                  @SerializedName("id") var id: Int?,
                  @SerializedName("original_language") var originalLanguage: String?,
                  @SerializedName("original_title") var originalTitle: String?,
                  @SerializedName("overview") var overview: String?,
                  @SerializedName("popularity") var popularity: Double?,
                  @SerializedName("poster_path") var posterPath: String?,
                  @SerializedName("production_companies") var productionCompanies: ArrayList<ProductionCompanies>?,
                  @SerializedName("release_date") var releaseDate: String?,
                  @SerializedName("status") var status: String?,
                  @SerializedName("tagline") var tagline: String?,
                  @SerializedName("title") var title: String?,
                  @SerializedName("vote_average") var rating: Float?) {


    override fun toString(): String {
        return "MovieDetail(adult=$adult, " +
                "backdropPath=$backdropPath, " +
                "genresList=$genresList, " +
                "homepage=$homepage, id=$id, " +
                "originalLanguage=$originalLanguage, " +
                "originalTitle=$originalTitle, " +
                "overview=$overview, " +
                "popularity=$popularity, " +
                "posterPath=$posterPath, " +
                "productionCompanies=$productionCompanies, " +
                "releaseDate=$releaseDate, " +
                "status=$status, " +
                "tagline=$tagline, " +
                "title=$title, " +
                "rating=$rating)"
    }
}