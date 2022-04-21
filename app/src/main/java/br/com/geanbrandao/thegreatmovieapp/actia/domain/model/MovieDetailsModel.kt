package br.com.geanbrandao.thegreatmovieapp.actia.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    @SerializedName("backdrop_path") val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreModel>,
    val id: Int,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String, // todo this could be an enum
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double
)