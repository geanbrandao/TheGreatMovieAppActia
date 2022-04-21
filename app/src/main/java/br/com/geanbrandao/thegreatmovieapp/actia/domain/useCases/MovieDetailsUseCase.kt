package br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases

import br.com.geanbrandao.thegreatmovieapp.actia.data.repository.MoviesRepository
import br.com.geanbrandao.thegreatmovieapp.actia.utils.State
import br.com.geanbrandao.thegreatmovieapp.actia.utils.extensions.resolveError
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MovieDetailsUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke(id: Int) = flow {
        emit(State.LoadingState(isLoading = true))
        delay(1000)
        try {
            emit(State.DataState(repository.getMovieDetails(id)))
        } catch (e: Exception) {
            // todo create logFile
            emit(e.resolveError())
        } finally {
            emit(State.LoadingState(isLoading = false))
        }
    }
}