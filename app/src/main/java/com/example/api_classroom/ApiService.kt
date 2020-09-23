package com.example.api_classroom

import com.example.api_classroom.Responses.LoginResponse
import com.example.api_classroom.Responses.PostsResponse
import com.example.api_classroom.requests.LoginRequest
import com.example.api_classroom.utils.Constants
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface for defining REST request functions
 */
interface ApiService {

    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.POSTS_URL)
    fun fetchPosts(): Call<PostsResponse>

}