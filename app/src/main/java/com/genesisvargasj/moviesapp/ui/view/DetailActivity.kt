package com.genesisvargasj.moviesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.genesisvargasj.moviesapp.databinding.ActivityDetailBinding
import com.genesisvargasj.moviesapp.ui.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        extras?.getInt("id")?.let { id -> movieViewModel.getMovie(id) }
        movieViewModel.movieModel.observe(this, Observer {
            Picasso.with(this)
                .load("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${it.backdrop_path}")
                .into(binding.imgPoster)
            binding.lblTitle.text = it.title
            binding.lblReleaseDate.text = it.release_date
            binding.lblOverview.text = it.overview
        })
        movieViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }
}