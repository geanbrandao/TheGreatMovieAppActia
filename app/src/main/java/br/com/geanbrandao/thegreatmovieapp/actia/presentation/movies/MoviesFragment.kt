package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.geanbrandao.thegreatmovieapp.actia.R
import br.com.geanbrandao.thegreatmovieapp.actia.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {

    private val binding: FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

}