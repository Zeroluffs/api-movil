package com.example.api_classroom.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String
)