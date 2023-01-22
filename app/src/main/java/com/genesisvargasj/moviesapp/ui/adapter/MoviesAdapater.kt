package com.genesisvargasj.moviesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genesisvargasj.moviesapp.R
import com.genesisvargasj.moviesapp.data.model.Movie
import com.genesisvargasj.moviesapp.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        fun bindItems(movie: Movie)  {
            binding.lblTitle.text = movie.title
            binding.lblReleaseDate.text = movie.release_date
            Picasso.with(context)
                .load("https://www.themoviedb.org/t/p/w220_and_h330_face/${movie.poster_path}")
                //.load("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${movie.backdrop_path}")
                .into(binding.imgPoster)
            itemView.setOnClickListener{
                onItemClick?.invoke(movie)
            }
        }
    }
}