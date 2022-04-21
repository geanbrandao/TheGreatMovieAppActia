package br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases

data class MoviesUseCase(
    val discoverMoviesUseCase: DiscoverMoviesUseCase,
    val movieDetailsUseCase: MovieDetailsUseCase,
)
