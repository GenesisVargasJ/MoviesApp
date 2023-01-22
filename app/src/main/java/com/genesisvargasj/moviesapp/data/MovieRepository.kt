package com.genesisvargasj.moviesapp.data

import com.genesisvargasj.moviesapp.data.model.Movie
import com.genesisvargasj.moviesapp.data.network.ApiClient
import com.genesisvargasj.moviesapp.data.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: ApiClient) {

    suspend fun getMovies(): List<Movie> {
        return api.getMovies()
    }

    suspend fun getMovie(id: Int): Movie {
        return api.getMovie(id)
    }
}