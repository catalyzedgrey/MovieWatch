package com.example.moviewatch.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatch.R
import com.example.moviewatch.data.InnerResults
import com.example.moviewatch.data.Results
import com.example.moviewatch.databinding.ListItemSearchBinding
import java.util.*

class SearchAdapter(
    var searchMovieList: List<InnerResults>,
    val callBack: IFavoriteMovie
) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    interface IFavoriteMovie {
        fun onFavoriteMovie(movie: InnerResults, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ListItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchMovieList[position], callBack)
        holder.binding.favButton.setOnClickListener {
            if (!searchMovieList[position].isFavorite) {
                searchMovieList[position].isFavorite = true
                callBack.onFavoriteMovie(searchMovieList[position], position)
            }
        }
    }

    fun submitList(results: Results) {
        searchMovieList = results.results
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return searchMovieList.size
    }

    inner class SearchViewHolder(val binding: ListItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InnerResults, callBack: IFavoriteMovie) {
            binding.searchTitle.text = item.title
            binding.rating.text = item.voteAverage.toString()
            Glide.with(this.itemView)
                .load("https://image.tmdb.org/t/p/w200/" + item.posterPath)
                .into(binding.searchPoster)

            if (item.isFavorite) {
                binding.favButton.setImageResource(R.drawable.ic_baseline_check_circle_outline_24)
            }


        }
    }

}