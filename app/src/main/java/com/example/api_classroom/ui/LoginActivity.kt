package com.example.api_classroom.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.Responses.DefaultResponse
import com.example.api_classroom.Responses.LoginResponse
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.User
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

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

            var request = ServiceBuilder.buildService(Api::class.java)
            var call = request.userLogin(email, password)
            call.enqueue(object :Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d("MyOut", "LOGGED OK")
                    val personLogged = User(response.body()!!.email, response.body()!!.username, response.body()!!.name, response.body()!!.token, response.body()!!.type)
                    SharedPrefManager.getInstance(applicationContext).saveUser(personLogged)
                    Log.d("MyOut", "WELCOME here  "+personLogged.name )

                    val intent = Intent(applicationContext, ProfileActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)


                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("MyOut", "SOME ERROR")
                }
            })
        }

        textViewRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        }


    }




}


