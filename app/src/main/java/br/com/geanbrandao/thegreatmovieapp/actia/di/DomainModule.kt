package br.com.geanbrandao.thegreatmovieapp.actia.di

import br.com.geanbrandao.thegreatmovieapp.actia.data.remote.MoviesApiService
import br.com.geanbrandao.thegreatmovieapp.actia.data.repository.MoviesRepository
import br.com.geanbrandao.thegreatmovieapp.actia.domain.repository.MoviesRepositoryImpl
import br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases.DiscoverMoviesUseCase
import br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases.MoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: MoviesApiService): MoviesRepository {
        return MoviesRepositoryImpl(api = apiService)
    }

    @Provides
    @Singleton
    fun provideMoviesUseCase(repository: MoviesRepository): MoviesUseCase {
        return MoviesUseCase(
            DiscoverMoviesUseCase(repository = repository)
        )
    }
}