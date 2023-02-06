package com.vimal.retrofitpostapi.model

import com.google.gson.annotations.SerializedName

data class Posts(
    @SerializedName("status")
    var status: Int,
    @SerializedName("Result")
    var data: List<PostResult>,
)

data class PostResult(
    @SerializedName("Title")
    var title: String,
    @SerializedName("Image")
    var image: String,
)