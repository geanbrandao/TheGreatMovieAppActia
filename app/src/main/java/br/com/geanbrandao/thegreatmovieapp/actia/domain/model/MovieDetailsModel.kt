package br.com.geanbrandao.thegreatmovieapp.actia.domain.model

data class MovieDetailsModel(
    val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreModel>,
    val id: Int,
    val originalTitle: String,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String,
    val revenue: Int,
    val status: String, // todo this could be an enum
    val title: String,
    val voteAverage: Double
)