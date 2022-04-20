package br.com.geanbrandao.thegreatmovieapp.actia.data.repository

import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel

interface MoviesRepository {
    suspend fun getDiscoverMovies(): DiscoverModel
}