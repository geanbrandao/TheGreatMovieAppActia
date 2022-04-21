package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.geanbrandao.thegreatmovieapp.actia.databinding.FragmentMoviesBinding
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel
import br.com.geanbrandao.thegreatmovieapp.actia.utils.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val adapter: MoviesAdapter = MoviesAdapter(onClickListener = {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie = it)
        findNavController().navigate(action)
    })

    private val binding: FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createListeners()
        fetchData()
    }

    private fun fetchData() {
        binding.componentInfo.llContentError.isVisible = INVISIBLE
        viewModel.getDiscoverMovies()
    }

    private fun createListeners() = with(binding) {
        collectFromViewModel()
        setupRecycler()
    }

    private fun setupRecycler() = with(binding)  {
        rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        rvMovies.adapter = adapter
    }

    private fun collectFromViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.movies.collect { response ->
                when(response) {
                    is State.LoadingState -> {
                        handleLoading(response.isLoading)
                    }
                    is State.DataState -> {
                        handleSuccess(response.data)
                    }
                    is State.ErrorState -> {
                        handleError(response.exception)
                    }
                }
            }
        }
    }

    private fun handleError(exception: Throwable) = with(binding) {
        componentInfo.root.isVisible = VISIBLE
        componentInfo.llContentError.isVisible = VISIBLE
        componentInfo.tvMessageError.text = exception.message
        componentInfo.btTryAgain.setOnClickListener {
            fetchData()
        }
    }

    private fun handleSuccess(model: DiscoverModel) = with(binding) {
        adapter.add(model.results.toCollection(ArrayList()))
        componentInfo.root.isVisible = INVISIBLE
    }

    private fun handleLoading(isLoading: Boolean) = with(binding) {
        componentInfo.pbLoading.isVisible = isLoading
    }

    companion object {
        private const val VISIBLE = true
        private const val INVISIBLE = false
    }
}