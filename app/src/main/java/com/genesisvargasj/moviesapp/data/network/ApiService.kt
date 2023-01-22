package com.genesisvargasj.moviesapp.data.network

import com.genesisvargasj.moviesapp.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /* POPULAR MOVIES */
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    /* GET A MOVIE BY ID */
    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int, @Query("api_key") apiKey: String): Response<Movie>
}