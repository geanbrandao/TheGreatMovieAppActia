package br.com.geanbrandao.thegreatmovieapp.actia.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultModel(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
): Serializable