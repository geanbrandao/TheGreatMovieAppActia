package br.com.geanbrandao.thegreatmovieapp.actia.domain.model

data class DiscoverModel(
    val page: Int,
    val results: List<ResultModel>,
    val totalPages: Int,
    val totalResults: Int
)