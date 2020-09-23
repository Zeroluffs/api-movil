package com.example.api_classroom.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.Course
import com.example.api_classroom.models.OneCourse
import com.example.api_classroom.models.Student
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_showonecourse.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewCourseAcitivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showonecourse)

        buttonSearchCourse.setOnClickListener{
            Log.d("MyOut", "please work")
            var courseId = editCourseId.text.toString().trim()
            if(courseId.isEmpty()){
                editCourseId.error="CourseId Required"
                editCourseId.requestFocus()
                return@setOnClickListener
            }

            var request = ServiceBuilder.buildService(Api::class.java)
            val userLogged = SharedPrefManager.getInstance(applicationContext).user
            val username = userLogged.username
            val token = userLogged.token
            val auth = "Bearer "+token
            var call = request.viewCourse(username,auth,courseId)
            Log.d("MyOut", "before call and course ID"+courseId)
            call.enqueue(object: Callback<OneCourse> {
                override fun onResponse(call: Call<OneCourse>, response: Response<OneCourse>) {
                    if(response.isSuccessful){
                        Log.d("MyOut", "what is this")
                        Log.d("MyOut", "This is the course name"+response.body()!!.name.toString())
                        textViewLogin.text = response.body()!!.name
                    }

                }

                override fun onFailure(call: Call<OneCourse>, t: Throwable) {
                    Log.d("MyOut", "Failure " + t.message)                }
            })
        }

        buttonGoBackMain.setOnClickListener {
    startActivity(Intent(this@ViewCourseAcitivity, ProfileActivity::class.java))
}

    }
}