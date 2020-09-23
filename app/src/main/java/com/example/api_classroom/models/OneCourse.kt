package com.example.api_classroom.models

 data class OneCourse (
     var name : String = "",
     var professor: User,
     var student: List<User>
 )