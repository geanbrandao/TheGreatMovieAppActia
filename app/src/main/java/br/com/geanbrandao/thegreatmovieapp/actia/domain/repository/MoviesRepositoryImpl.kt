package br.com.geanbrandao.thegreatmovieapp.actia.domain.repository

import br.com.geanbrandao.thegreatmovieapp.actia.data.remote.MoviesApiService
import br.com.geanbrandao.thegreatmovieapp.actia.data.repository.MoviesRepository
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.MovieDetailsModel
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants.API_KEY
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val api: MoviesApiService) : MoviesRepository {
    override suspend fun getDiscoverMovies(): DiscoverModel = api.getDiscoverMovie(API_KEY)
    override suspend fun getMovieDetails(id: Int): MovieDetailsModel = api.getMovieDetails(API_KEY, id)
}