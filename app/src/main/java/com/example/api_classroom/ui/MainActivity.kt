package com.example.api_classroom.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.api_classroom.API.Api
import com.example.api_classroom.ApiClient
import com.example.api_classroom.R
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.Responses.DefaultResponse
import com.example.api_classroom.utils.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        textViewLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val name= editTextName.text.toString().trim()
            val username = editTextUsername.text.toString().trim()

            if(email.isEmpty()){
                editTextEmail.error="Email Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                editTextPassword.error="Password Required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }
            if(name.isEmpty()){
            editTextName.error="Name Required"
            editTextName.requestFocus()
            return@setOnClickListener
        }
            if(username.isEmpty()){
                editTextUsername.error="Username Required"
                editTextUsername.requestFocus()
                return@setOnClickListener
            }

            val request = ServiceBuilder.buildService(Api::class.java)
            val call = request.signUp(email, name, password, username)
            call.enqueue(object : Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Log.d("MyOut", "Failure " + t.message)

                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                   if(response.isSuccessful){
                       Log.d("MyOut", "OK")
                   }
                }
            })

        }

    }



}