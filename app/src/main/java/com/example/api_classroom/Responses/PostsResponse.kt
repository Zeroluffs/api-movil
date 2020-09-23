package com.example.api_classroom.Responses
import com.example.api_classroom.models.Post
import com.google.gson.annotations.SerializedName

data class PostsResponse (
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("posts")
    var posts: List<Post>
)