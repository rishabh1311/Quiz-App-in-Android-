package com.example.quizproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {


    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<QuestionBank>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer: Int = 0
    private var mUserNmae: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserNmae = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        setQuestions()

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val tv_progress: TextView = findViewById(R.id.tv_progress)
        val tv_question: TextView = findViewById(R.id.top_question)
        val iv_image: ImageView = findViewById(R.id.iv_image)
        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)
        var btn_submit: Button = findViewById(R.id.btn_submit)

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


    }

    private fun setQuestions(){
        val question: QuestionBank = mQuestionsList!![mCurrentPosition -1]

        defaultOptionView()



        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val tv_progress: TextView = findViewById(R.id.tv_progress)
        val tv_question: TextView = findViewById(R.id.top_question)
        val iv_image: ImageView = findViewById(R.id.iv_image)
        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)
        var btn_submit: Button = findViewById(R.id.btn_submit)

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question!!.question

        iv_image.setImageResource(question.image)

        tv_option_one.text = question.opt1
        tv_option_two.text = question.opt2
        tv_option_three.text = question.opt3
        tv_option_four.text = question.opt4
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val tv_progress: TextView = findViewById(R.id.tv_progress)
        val tv_question: TextView = findViewById(R.id.top_question)
        val iv_image: ImageView = findViewById(R.id.iv_image)
        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)

        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val tv_progress: TextView = findViewById(R.id.tv_progress)
        val tv_question: TextView = findViewById(R.id.top_question)
        val iv_image: ImageView = findViewById(R.id.iv_image)
        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)
        var btn_submit: Button = findViewById(R.id.btn_submit)

        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestions()
                        }else -> {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserNmae)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                        startActivity(intent)

                    }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0

                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){

        val tv_option_one: TextView = findViewById(R.id.tv_option_one)
        val tv_option_two: TextView = findViewById(R.id.tv_option_two)
        val tv_option_three: TextView = findViewById(R.id.tv_option_three)
        val tv_option_four: TextView = findViewById(R.id.tv_option_four)

        when(answer){
            1 ->{
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 ->{
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 ->{
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 ->{
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.select_option_border_bg)
    }

}

