package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.MovieDetailsModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases.MoviesUseCase
import br.com.geanbrandao.thegreatmovieapp.actia.utils.State
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieDetailsViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
): ViewModel() {

    private var job: Job? = null
    val movieDetails = MutableStateFlow<State<MovieDetailsModel>>(State.LoadingState(isLoading = true))

    fun getMovieDetails(id: Int) {
        job = moviesUseCase.movieDetailsUseCase(id)
            .onEach { movieDetails.value = it }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}