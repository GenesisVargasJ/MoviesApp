package com.genesisvargasj.moviesapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.genesisvargasj.moviesapp.databinding.ActivityMainBinding
import com.genesisvargasj.moviesapp.ui.viewmodel.MovieViewModel
import com.genesisvargasj.moviesapp.ui.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel.getMovies()
        movieViewModel.moviesModel.observe(this, Observer {
            val adapter = MoviesAdapter(it)
            binding.lstMovies.layoutManager = GridLayoutManager (this, 2)
            binding.lstMovies.hasFixedSize()
            binding.lstMovies.adapter = adapter
            adapter.onItemClick = {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("id", it.id)
                startActivity(intent)
            }
        })
        movieViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }
}