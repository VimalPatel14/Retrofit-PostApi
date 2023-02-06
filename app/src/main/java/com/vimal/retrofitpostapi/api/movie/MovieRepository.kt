package com.vimal.retrofitpostapi.api.movie

import com.vimal.retrofitpostapi.api.NetworkState
import com.vimal.retrofitpostapi.model.Movie

class MovieRepository constructor(private val retrofitService: MovieRetrofitService) {
    suspend fun getAllMovies(): NetworkState<List<Movie>> {
        val response = retrofitService.getAllMovies()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}