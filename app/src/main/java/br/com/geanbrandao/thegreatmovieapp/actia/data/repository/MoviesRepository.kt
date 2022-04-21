package br.com.geanbrandao.thegreatmovieapp.actia.data.repository

import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.MovieDetailsModel

interface MoviesRepository {
    suspend fun getDiscoverMovies(): DiscoverModel
    suspend fun getMovieDetails(id: Int): MovieDetailsModel
}