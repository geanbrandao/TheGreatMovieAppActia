package br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases

import br.com.geanbrandao.thegreatmovieapp.actia.data.repository.MoviesRepository
import br.com.geanbrandao.thegreatmovieapp.actia.utils.State
import br.com.geanbrandao.thegreatmovieapp.actia.utils.extensions.resolveError
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class DiscoverMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke() = flow {
        emit(State.LoadingState(isLoading = true))
        delay(1000)
        try {
            emit(State.DataState(repository.getDiscoverMovies()))
        } catch (e: Exception) {
            // todo create logFile
            emit(e.resolveError())
        } finally {
            emit(State.LoadingState(isLoading = false))
        }
    }
}