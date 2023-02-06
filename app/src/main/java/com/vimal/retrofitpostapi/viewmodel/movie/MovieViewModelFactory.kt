package com.vimal.retrofitpostapi.viewmodel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vimal.retrofitpostapi.api.movie.MovieRepository

class MovieViewModelFactory constructor(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            MovieViewModel(this.repository) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}