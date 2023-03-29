package com.example.quizapp.model

import com.example.quizapp.R

object Constants {


    const val USER_NAME :String = "user_name"
    const val TOTAL_QUESTIONS :String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,"What country does this flag belong to?", R.drawable.korea,
            "Koreas","Australia","South Korea","Brazil",
            3
        )
        questionsList.add(que1)

        val que2 = Question(
            1,"What country does this flag belong to?", R.drawable.nepal,
            "Honduras","Nepal","South Africa","Palau",
            2
        )
        questionsList.add(que2)

        val que3 = Question(
            1,"What country does this flag belong to?", R.drawable.hongkong,
            "HongKong","Tai-Pei","China","BurkinaFaso",
            1
        )
        questionsList.add(que3)

        val que4 = Question(
            1,"What country does this flag belong to?", R.drawable.kenya,
            "Dominican","Australia","Nepal","Kenya",
            4
        )
        questionsList.add(que4)

        val que5 = Question(
            1,"What country does this flag belong to?", R.drawable.dominican,
            "Honduras","Palau","Nepal","Dominican",
            4
        )
        questionsList.add(que5)
        return questionsList
    }
}