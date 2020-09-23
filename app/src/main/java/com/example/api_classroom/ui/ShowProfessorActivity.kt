package com.example.api_classroom.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_classroom.API.Api
import com.example.api_classroom.R
import com.example.api_classroom.ServiceBuilder
import com.example.api_classroom.models.Course
import com.example.api_classroom.models.ExampleAdapter
import com.example.api_classroom.models.Student
import com.example.api_classroom.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_showcourses.*
import kotlinx.android.synthetic.main.activity_showstudents.*
import kotlinx.android.synthetic.main.activity_showstudents.buttonMain
import kotlinx.android.synthetic.main.activity_showstudents.recycler_view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowProfessorActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showprofessors)
        val userLogged = SharedPrefManager.getInstance(applicationContext).user
        val username = userLogged.username
        val token = userLogged.token
        val auth = "Bearer "+token
        val request = ServiceBuilder.buildService(Api::class.java)
        val call = request.getProfessors(username,auth)
        call.enqueue(object : Callback<List<Student>> {
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                Log.d("MyOut", "Failure " + t.message)

            }

            override fun onResponse(
                call: Call<List<Student>>,
                response: Response<List<Student>>
            ) {
                if(response.isSuccessful){
                    Log.d("MyOut", "these are the courses"+response.body().toString())

                    val studentList  = response.body()

                    var listItems = arrayOfNulls<String>(studentList!!.size)
                    var listItems2 = arrayOfNulls<String>(studentList!!.size)
                    var listItems3 = arrayOfNulls<String>(studentList!!.size)
                    var listItems4 = arrayOfNulls<String>(studentList!!.size)

                    for (i in 0 until listItems.size){
                        val student = studentList[i]
                        listItems[i] = student.name
                        listItems2[i] = student.id
                        listItems3[i] = student.email
                        listItems4[i] = student.username
                        Log.d("MyOut", "Course names"+student.name)
                    }

                    recycler_view.adapter = ExampleAdapter(listItems, listItems2,listItems3, listItems4)
                    recycler_view.layoutManager = LinearLayoutManager(this@ShowProfessorActivity)
                    recycler_view.setHasFixedSize(true)


                }
            }
        })

        buttonMain.setOnClickListener {
            startActivity(Intent(this@ShowProfessorActivity, ProfileActivity::class.java))
        }

    }
}