package br.com.geanbrandao.thegreatmovieapp.actia.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.geanbrandao.thegreatmovieapp.actia.R
import br.com.geanbrandao.thegreatmovieapp.actia.databinding.ItemMovieBinding
import br.com.geanbrandao.thegreatmovieapp.actia.domain.model.ResultModel
import br.com.geanbrandao.thegreatmovieapp.actia.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MoviesAdapter(
    private val onClickListener: (item: ResultModel) -> Unit,
    private val data: ArrayList<ResultModel> = arrayListOf()
): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClickListener.invoke(item)
        }
    }

    override fun getItemCount(): Int = data.count()

    fun add(list: ArrayList<ResultModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovieBinding.bind(itemView)

        fun bind(item: ResultModel) = with(binding) {
            tvName.text = item.title
            tvReleaseYear.text = item.releaseDate
            Glide.with(itemView.context)
                .load(Constants.BASE_URL_IMAGES.plus(item.posterPath))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.ic_broken_image)
                .into(ivMovie)
        }
    }
}