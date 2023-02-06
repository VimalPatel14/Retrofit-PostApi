package com.vimal.retrofitpostapi.api.post

import com.vimal.retrofitpostapi.api.NetworkState
import com.vimal.retrofitpostapi.model.Posts

class PostRepository constructor(private val retrofitService: PostRetrofitService) {
    suspend fun getAllMovies(): NetworkState<Posts> {
        val response = retrofitService.getAllPost()
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