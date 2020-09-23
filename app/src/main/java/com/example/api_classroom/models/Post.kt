package com.example.api_classroom.models
import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var name: String,

    @SerializedName("description")
    var professor: String,

    @SerializedName("content")
    var students: String
)