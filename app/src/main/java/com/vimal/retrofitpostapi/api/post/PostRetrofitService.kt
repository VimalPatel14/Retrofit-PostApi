package com.vimal.retrofitpostapi.api.post

import com.vimal.retrofitpostapi.model.Posts
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostRetrofitService {

    @GET("demo_api.json")
    suspend fun getAllPost() : Response<Posts>

    companion object {
        var retrofitService: PostRetrofitService? = null
        fun getInstance(baseurl: String) : PostRetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(PostRetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}