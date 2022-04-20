package br.com.geanbrandao.thegreatmovieapp.actia.data.remote

import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.MovieDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    @GET(DISCOVER_MOVIE)
    suspend fun getDiscoverMovie(@Query("api_key") apiKey: String): DiscoverModel

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsModel

    companion object {
        private const val DISCOVER = "discover"
        private const val DISCOVER_MOVIE = "$DISCOVER/movie"
        private const val MOVIE_DETAILS = "movie/{id}"
    }
}