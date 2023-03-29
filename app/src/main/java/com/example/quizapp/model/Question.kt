package com.example.quizapp.model

data class Question(
    val id: Int,
    val question: String,
    val image:Int,

    val options1:String,
    val options2:String,
    val options3:String,
    val options4:String,
    val correctAnswer:Int
)
