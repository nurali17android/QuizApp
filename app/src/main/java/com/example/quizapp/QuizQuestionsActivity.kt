package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.quizapp.model.Constants
import com.example.quizapp.model.Question

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition :Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionSelection : Int = 0
    private var userName : String? = null
    private var correctAnswers : Int = 0

    private var progressBar:ProgressBar? = null
    private var tvProgress :TextView? = null    // 0/9
    private var tvQuestion :TextView? = null
    private var tvImage :ImageView? = null
    private var btnSubmit : Button? = null

    private var option1 :TextView? = null
    private var option2 :TextView? = null
    private var option3 :TextView? = null
    private var option4 :TextView? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        tvImage = findViewById(R.id.image)
        btnSubmit = findViewById(R.id.submit)
        option1 = findViewById(R.id.tv_option1)
        option2 = findViewById(R.id.tv_option2)
        option3 = findViewById(R.id.tv_option3)
        option4 = findViewById(R.id.tv_option4)

        option1?.setOnClickListener(this)
        option2?.setOnClickListener(this)
        option3?.setOnClickListener(this)
        option4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
    }
    @SuppressLint("SetTextI18n")
     fun setQuestion(){
        defaultOptionsView()
        val question : Question = mQuestionsList!![mCurrentPosition-1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition / ${progressBar?.max}"
        tvQuestion?.text = question.question

        option1?.text = question.options1
        option2?.text = question.options2
        option3?.text = question.options3
        option4?.text = question.options4

        tvImage?.setImageResource(question.image)

        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "Finish"
        }else{
            btnSubmit?.text = "Submit"
        }
    }

    private fun defaultOptionsView(){

        val options = ArrayList<TextView>()
            option1?.let {
                options.add(0,it)
            }
            option2?.let {
                options.add(1,it)
            }
            option3?.let {
                options.add(2,it)
            }
            option4?.let {
                options.add(3,it)
            }

        for(textView in options){
            textView.setTextColor(Color.parseColor("#7A8089"))
            textView.typeface = Typeface.DEFAULT
            textView.background = ContextCompat.getDrawable(
                this,R.drawable.option_bg
            )
        }
    }
    private fun selectedOptionView(textView: TextView,selected:Int){
            defaultOptionsView()
        mSelectedOptionSelection = selected
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface,Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,R.drawable.selected_optinon_bg
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option1 -> {
                option1?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option2 -> {
                option2?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option3 -> {
                option3?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option4-> {
                option4?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.submit ->{
                if(mSelectedOptionSelection == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition<=mQuestionsList!!.size->{
                            setQuestion()
                        }
                        else ->{
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,userName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer != mSelectedOptionSelection){
                        answerView(mSelectedOptionSelection,R.drawable.wrong_option_border)
                    }else{
                        correctAnswers++
                    }
                        answerView(question.correctAnswer,R.drawable.correct_option_border)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "Finish"
                    }else{
                        btnSubmit?.text = "Go to the next question"
                    }
                    mSelectedOptionSelection = 0
                }
            }
        }
    }
    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1->{
                option1?.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            2->{
                option2?.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            3->{
                option3?.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
            4->{
                option4?.background = ContextCompat.getDrawable(
                    this,drawableView
                )
            }
        }
    }
}