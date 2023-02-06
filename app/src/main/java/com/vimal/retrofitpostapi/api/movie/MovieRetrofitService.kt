package com.vimal.retrofitpostapi.api.movie

import com.vimal.retrofitpostapi.model.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieRetrofitService {

    @GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movie>>

    companion object {
        var retrofitService: MovieRetrofitService? = null
        fun getInstance(baseurl: String) : MovieRetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MovieRetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}