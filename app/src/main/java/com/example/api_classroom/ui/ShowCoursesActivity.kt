package com.example.api_classroom.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.Course
import com.example.api_classroom.models.ExampleAdapter
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_showcourses.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowCoursesActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcourses)
        val userLogged = SharedPrefManager.getInstance(applicationContext).user
        val username = userLogged.username
        val token = userLogged.token
        val auth = "Bearer "+token
        val request = ServiceBuilder.buildService(Api::class.java)
        val call = request.getCourses(username,auth)
        call.enqueue(object : Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.d("MyOut", "Failure " + t.message)

            }

            override fun onResponse(
                call: Call<List<Course>>,
                response: Response<List<Course>>
            ) {
                if(response.isSuccessful){
                    Log.d("MyOut", "these are the courses"+response.body().toString())

                    val courseList  = response.body()

                    var listItems = arrayOfNulls<String>(courseList!!.size)
                    var listItems2 = arrayOfNulls<String>(courseList!!.size)
                    var listItems3 = arrayOfNulls<String>(courseList!!.size)
                    var listItems4 = arrayOfNulls<String>(courseList!!.size)

                    for (i in 0 until listItems.size){
                        val course = courseList[i]
                        listItems[i] = course.name
                        listItems2[i] = course.id
                        listItems3[i] = course.professor
                        listItems4[i] = course.students
                        Log.d("MyOut", "Course names"+course.name)
                    }

                    recycler_view.adapter = ExampleAdapter(listItems, listItems2,listItems3, listItems4)
                    recycler_view.layoutManager = LinearLayoutManager(this@ShowCoursesActivity)
                    recycler_view.setHasFixedSize(true)


                }
            }
        })


        buttonMain.setOnClickListener {
            startActivity(Intent(this@ShowCoursesActivity, ProfileActivity::class.java))
        }
    }
}

