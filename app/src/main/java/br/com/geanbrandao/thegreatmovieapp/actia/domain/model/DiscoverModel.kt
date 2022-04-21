package br.com.geanbrandao.thegreatmovieapp.actia.domain.model

import com.google.gson.annotations.SerializedName

data class DiscoverModel(
    val page: Int,
    val results: List<ResultModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)