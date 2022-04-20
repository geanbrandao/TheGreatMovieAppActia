package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movieDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.geanbrandao.thegreatmovieapp.actia.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }

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
    }

    private fun createListeners() {
        binding.componentToolbar.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}