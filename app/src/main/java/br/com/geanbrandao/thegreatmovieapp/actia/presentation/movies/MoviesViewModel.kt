package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.useCases.MoviesUseCase
import br.com.geanbrandao.thegreatmovieapp.actia.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
): ViewModel() {

    private var job: Job? = null
    val movies = MutableStateFlow<State<DiscoverModel>>(State.LoadingState(isLoading = true))

    fun getDiscoverMovies() {
        job = moviesUseCase.discoverMoviesUseCase()
            .onEach {
                movies.value = it
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}