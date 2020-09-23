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
import kotlinx.android.synthetic.main.activity_showoneprofessor.*
import kotlinx.android.synthetic.main.activity_showonestudent.*
import kotlinx.android.synthetic.main.activity_showonestudent.buttonGoBackMain2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewProfessorActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showoneprofessor)

        buttonSearchProfessor.setOnClickListener{
            Log.d("MyOut", "please work")
            var studentId = editProfessorId.text.toString().trim()
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
            var call = request.viewProfessor(username,auth,studentId)
            Log.d("MyOut", "before call and course ID"+studentId)
            call.enqueue(object: Callback<Student> {
                override fun onResponse(call: Call<Student>, response: Response<Student>) {
                    if(response.isSuccessful){
                        Log.d("MyOut", "what is this")
                        Log.d("MyOut", "This is the course name"+response.body()!!.name.toString())
                        textViewName2.text = response.body()!!.name
                        textViewUsername2.text= response.body()!!.username
                        textViewCity2.text = response.body()!!.city
                        textViewPhone2.text = response.body()!!.phone
                        textViewBirthday2.text = response.body()!!.birthday


                    }

                }

                override fun onFailure(call: Call<Student>, t: Throwable) {
                    Log.d("MyOut", "Failure " + t.message)                }
            })
        }

        buttonGoBackMain3.setOnClickListener {
            startActivity(Intent(this@ViewProfessorActivity, ProfileActivity::class.java))
        }

    }
}