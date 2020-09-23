package com.example.api_classroom.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.Responses.CourseResponse
import com.example.api_classroom.Responses.DefaultResponse
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.Course
import com.example.api_classroom.models.Student
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        buttonAddCourse.setOnClickListener {
            var request = ServiceBuilder.buildService(Api::class.java)
            val userLogged = SharedPrefManager.getInstance(applicationContext).user
            val username = userLogged.username
            val token = userLogged.token
            val auth = "Bearer "+token
            var call = request.addCourse(username,auth)
            call.enqueue(object: Callback<Course>{
                override fun onResponse(call: Call<Course>, response: Response<Course>) {
                    if(response.isSuccessful){
                        Log.d("MyOut", "Course Added!")
                    }

                }

                override fun onFailure(call: Call<Course>, t: Throwable) {
                    Log.d("MyOut", "Failure " + t.message)                }
            })
        }

        buttonAddStudents.setOnClickListener {
            Log.d("MyOut", "Button was pressed " )
            startActivity(Intent(this@ProfileActivity, AddStudentActivity::class.java))

        }

        buttonShowStudents.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ShowCoursesActivity::class.java))

        }

        buttonShowStudents2.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ShowStudentsActivity::class.java))
        }

        buttonLogout.setOnClickListener {
            SharedPrefManager.getInstance(applicationContext).clear()
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
            finish()
        }

        buttonShowProfessors.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ShowProfessorActivity::class.java))

        }

        buttonShowOneCourse.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ViewCourseAcitivity::class.java))
        }

        buttonShowOneStudent.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ViewStudentActivity::class.java))

        }
        buttonShowOneProfessor.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ViewProfessorActivity::class.java))

        }

    }

    override fun onStart() {
        super.onStart()

        var userinfo = SharedPrefManager.getInstance(applicationContext).user
        Log.d("MyOut", "HENLO  "+userinfo.token )
    }

    }
