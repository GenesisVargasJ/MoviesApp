package com.genesisvargasj.moviesapp.data.network

import com.genesisvargasj.moviesapp.data.model.Movie
import com.genesisvargasj.moviesapp.utils.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiClient @Inject constructor(private val api: ApiService) {

    suspend fun getMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            val response = api.getMovies(AppConstants.apiKey)
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getMovie(id: Int): Movie {
        return withContext(Dispatchers.IO) {
            val response = api.getMovie(id, AppConstants.apiKey)
            response.body() as Movie
        }
    }
}