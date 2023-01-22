package com.genesisvargasj.moviesapp.domain

import com.genesisvargasj.moviesapp.data.MovieRepository
import com.genesisvargasj.moviesapp.data.model.Movie
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(id: Int): Movie = movieRepository.getMovie(id)
}