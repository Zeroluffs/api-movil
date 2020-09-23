package com.example.api_classroom

import android.content.Context
import com.example.api_classroom.API.Api
import com.example.api_classroom.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
          //  .client(okHttpClient(context))
            .build()

        retrofit.create(Api::class.java)
    }
}