package com.genesisvargasj.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesisvargasj.moviesapp.data.model.Movie
import com.genesisvargasj.moviesapp.domain.GetMoviesUseCase
import com.genesisvargasj.moviesapp.domain.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    val moviesModel = MutableLiveData<List<Movie>>()
    val movieModel = MutableLiveData<Movie>()
    val isLoading = MutableLiveData<Boolean>()

    fun getMovies() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMoviesUseCase()
            if (result.isNotEmpty()) {
                moviesModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun getMovie(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getMovieUseCase(id)
            movieModel.postValue(result)
            isLoading.postValue(false)
        }
    }
}