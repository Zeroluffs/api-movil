package com.example.api_classroom.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.Student
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_addstudent.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddStudentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstudent)

        buttonAddStudentWithID.setOnClickListener {
            val courseId = editcourseStudentID.text.toString().trim()

            if(courseId.isEmpty()){
                editTextEmail.error="ID Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            var request = ServiceBuilder.buildService(Api::class.java)
            val userLogged = SharedPrefManager.getInstance(applicationContext).user
            val username = userLogged.username
            val token = userLogged.token
            val auth = "Bearer "+token
            var call = request.addStudent(username,auth,courseId)
            call.enqueue(object: Callback<Student> {
                override fun onResponse(call: Call<Student>, response: Response<Student>) {
                    if(response.isSuccessful){
                        Log.d("MyOut", "Student Added!")
                        startActivity(Intent(this@AddStudentActivity, ProfileActivity::class.java))
                    }

                }

                override fun onFailure(call: Call<Student>, t: Throwable) {
                    Log.d("MyOut", "Failure " + t.message)                }
            })


        }

    }
}