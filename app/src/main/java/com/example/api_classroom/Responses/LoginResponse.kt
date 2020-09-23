package com.example.api_classroom.Responses
import com.example.api_classroom.models.User
import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("type")
    var type: String,

    @SerializedName("token")
    var token: String,

    @SerializedName("username")
    var username: String,

    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String


)