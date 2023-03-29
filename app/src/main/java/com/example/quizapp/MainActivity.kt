package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quizapp.model.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart :Button = findViewById(R.id.btn_start)
        val editName : EditText = findViewById(R.id.editText)

        buttonStart.setOnClickListener {
            if(editName.text.isEmpty()){
                Toast.makeText(this,
                    "Please enter your name",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME,editName.text.toString())
                startActivity(intent)
                finish() //if you don't want to back to MainActivity(welcome_page)
            }
        }
    }
}