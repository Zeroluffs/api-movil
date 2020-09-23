package com.example.api_classroom.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.OneCourse
import com.example.api_classroom.models.Student
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_showonecourse.*
import kotlinx.android.synthetic.main.activity_showonestudent.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewStudentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showonestudent)

        buttonSearchStudent.setOnClickListener{
            Log.d("MyOut", "please work")
            var studentId = editStudentId.text.toString().trim()
            if(studentId.isEmpty()){
                editStudentId.error="StudentId Required"
                editStudentId.requestFocus()
                return@setOnClickListener
            }

            var request = ServiceBuilder.buildService(Api::class.java)
            val userLogged = SharedPrefManager.getInstance(applicationContext).user
            val username = userLogged.username
            val token = userLogged.token
            val auth = "Bearer "+token
            var call = request.viewStudent(username,auth,studentId)
            Log.d("MyOut", "before call and course ID"+studentId)
            call.enqueue(object: Callback<Student> {
                override fun onResponse(call: Call<Student>, response: Response<Student>) {
                    if(response.isSuccessful){
                        Log.d("MyOut", "what is this")
                        Log.d("MyOut", "This is the course name"+response.body()!!.name.toString())
                        textViewName.text = response.body()!!.name
                        textViewUsername.text= response.body()!!.username
                        textViewCity.text = response.body()!!.city
                        textViewPhone.text = response.body()!!.phone
                        textViewBirthday.text = response.body()!!.birthday


                    }

                }

                override fun onFailure(call: Call<Student>, t: Throwable) {
                    Log.d("MyOut", "Failure " + t.message)                }
            })
        }

        buttonGoBackMain2.setOnClickListener {
            startActivity(Intent(this@ViewStudentActivity, ProfileActivity::class.java))
        }



    }
}