package com.genesisvargasj.moviesapp.domain

import com.genesisvargasj.moviesapp.data.model.Movie
import com.genesisvargasj.moviesapp.data.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(): List<Movie> = movieRepository.getMovies()
}