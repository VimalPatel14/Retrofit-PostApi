package com.vimal.retrofitpostapi.api

import com.vimal.retrofitpostapi.model.Movie

object ValidationUtil {
    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }
}