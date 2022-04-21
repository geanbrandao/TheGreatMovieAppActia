package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.geanbrandao.thegreatmovieapp.actia.R
import br.com.geanbrandao.thegreatmovieapp.actia.databinding.FragmentMovieDetailsBinding
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.DiscoverModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.MovieDetailsModel
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.ResultModel
import br.com.geanbrandao.thegreatmovieapp.actia.presentation.movies.MoviesFragment
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants.BASE_URL_IMAGES
import br.com.geanbrandao.thegreatmovieapp.actia.utils.State
import br.com.geanbrandao.thegreatmovieapp.actia.utils.extensions.toYearDate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel: MovieDetailsViewModel by viewModels()

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
        handleArgs(args.movie)
        fetchData()
    }

    private fun fetchData() {
        binding.componentInfo.llContentError.isVisible = INVISIBLE
        viewModel.getMovieDetails(args.movie.id)
    }

    private fun handleArgs(movie: ResultModel) = with(binding) {
        componentToolbar.tvTitle.text = movie.title
        tvScore.text = movie.voteAverage.toString()
        tvReleaseDate.text = movie.releaseDate
        tvDescription.text = movie.overview
        Glide.with(requireContext())
            .load(BASE_URL_IMAGES+movie.backdropPath)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.ic_movie)
            .into(ivBackdrop)
    }

    private fun createListeners() {
        collectFromViewModel()
        binding.componentToolbar.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun collectFromViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.movieDetails.collect { response ->
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

    private fun handleSuccess(model: MovieDetailsModel) = with(binding) {
        componentToolbar.tvTitle.text = model.title
        tvStatus.text = model.status
        tvDuration.text = getString(R.string.fragment_movie_details_duration, model.runtime.toString())
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