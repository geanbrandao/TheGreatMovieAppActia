package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.geanbrandao.thegreatmovieapp.actia.R
import br.com.geanbrandao.thegreatmovieapp.actia.databinding.FragmentMovieDetailsBinding
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.ResultModel
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants.BASE_URL_IMAGES
import br.com.geanbrandao.thegreatmovieapp.actia.utils.extensions.toYearDate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        handleArgs(args.movie)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createListeners()
    }

    private fun handleArgs(movie: ResultModel) = with(binding) {
        componentToolbar.tvTitle.text = movie.title
        tvScore.text = movie.voteAverage.toString()
        tvReleaseYear.text = movie.releaseDate.toYearDate()
        tvDescription.text = movie.overview
        Glide.with(requireContext())
            .load(BASE_URL_IMAGES+movie.backdropPath)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.ic_movie)
            .into(ivBackdrop)
    }

    private fun createListeners() {
        binding.componentToolbar.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}