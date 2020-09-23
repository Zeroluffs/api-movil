package com.example.api_classroom.API

import com.example.api_classroom.Responses.LoginResponse
import com.example.api_classroom.Responses.DefaultResponse
import com.example.api_classroom.models.Course
import com.example.api_classroom.models.OneCourse
import com.example.api_classroom.models.Student
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("signup")
    fun signUp(
        @Field("email") email:String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("username") username: String
    ):Call<DefaultResponse>


    @FormUrlEncoded
    @POST("signin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ):Call<LoginResponse>

    @GET("{dbId}/courses")
    fun getCourses(
        @Path("dbId") user:String, @Header("Authorization") header: String ):Call<List<Course>>

    @POST("{dbId}/courses")
    fun addCourse(
        @Path("dbId") user: String, @Header("Authorization") header: String
    ):Call<Course>

    @FormUrlEncoded
    @POST("{dbId}/students")
    fun addStudent(
        @Path("dbId") user: String,
        @Header("Authorization") header: String,
        @Field("courseId")courseId: String
    ):Call<Student>

    @GET("{dbId}/students")
    fun getStudents(
        @Path("dbId") user:String, @Header("Authorization") header: String ):Call<List<Student>>

    @GET("{dbId}/professors")
    fun getProfessors(
        @Path("dbId") user:String, @Header("Authorization") header: String ):Call<List<Student>>

    @GET("{dbId}/courses/{courseId}")
    fun viewCourse(
        @Path("dbId") user: String, @Header("Authorization") header: String,
        @Path("courseId") courseId: String
    ):Call<OneCourse>
    @GET("{dbId}/students/{studentId}")
    fun viewStudent(
        @Path("dbId") user: String, @Header("Authorization") header: String,
        @Path("studentId") courseId: String
    ):Call<Student>
    @GET("{dbId}/professors/{professorId}")
    fun viewProfessor(
        @Path("dbId") user: String, @Header("Authorization") header: String,
        @Path("professorId") professorId: String
    ):Call<Student>
}