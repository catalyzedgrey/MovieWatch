package com.example.moviewatch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatch.data.InnerResults
import com.example.moviewatch.data.Results
import com.example.moviewatch.databinding.ListItemMovieBinding


class TrendingMediaAdapter(var trendingMovieList: List<InnerResults>) :
    RecyclerView.Adapter<TrendingMediaAdapter.TrendingMediaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMediaViewHolder {

        return TrendingMediaViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun submitList(results: Results) {
        trendingMovieList = results.results
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TrendingMediaViewHolder, position: Int) {
        holder.bind(trendingMovieList[position])

    }

    override fun getItemCount(): Int {
        return trendingMovieList.size
    }

    inner class TrendingMediaViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: InnerResults) {
            binding.movieName.text = item.title
            if (item.posterPath != null)
                Glide.with(this.itemView)
                    .load("https://image.tmdb.org/t/p/w200/" + item.posterPath)
                    .into(binding.poster)

        }
    }

}

