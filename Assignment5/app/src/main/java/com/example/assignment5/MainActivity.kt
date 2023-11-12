package com.example.assignment5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.assignment5.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val questions = mutableListOf<Question>()
    private val userAnswers = mutableMapOf<Int, MutableList<Int>>()
    private val realAnswers = mutableMapOf<Int, List<Int>>()
    private lateinit var questionsAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createQuestions()
        initViews()
    }

    private fun createQuestions() {
        questions.add(
            // Kotlin fundamental question
            Question(
                0,
                "What is the difference between val and var in Kotlin?",
                listOf(0),
                listOf(
                    "val is immutable and var is mutable",
                    "val is mutable and var is immutable",
                    "val is not nullable and var is nullable",
                    "val is nullable and var is not nullable"
                ),
                QuestionType.RADIO
            )
        )
        // kotlin fundamental checkbox question
        questions.add(
            Question(
                1,
                "What are the different types of variables in Kotlin?",
                listOf(0, 1, 2),
                listOf("var", "val", "const", "let"),
                QuestionType.CHECKBOX
            )
        )

        for (question in questions) {
            realAnswers[question.id] = question.answers
        }

    }

    private fun initViews() {
        binding.btnReset.setOnClickListener { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
        initQuestionsAdapter()
    }

    private fun initQuestionsAdapter() {
        questionsAdapter = QuestionAdapter(this, questions) { question, checkedId, isChecked ->

            when (question.questionType) {
                QuestionType.CHECKBOX -> {
                    if (userAnswers[question.id] == null) {
                        userAnswers[question.id] = mutableListOf()
                    }

                    if (isChecked) {
                        userAnswers[question.id]?.add(checkedId)
                    } else {
                        userAnswers[question.id]?.remove(checkedId)
                    }
                }

                QuestionType.RADIO -> {
                    val questionId = getQuestionId(checkedId)
                    userAnswers[question.id] = mutableListOf(questionId)
                }
            }
        }
        binding.lvQuestions.adapter = questionsAdapter
    }

    private fun getQuestionId(checkedId: Int): Int {
        return when (checkedId) {
            R.id.rb_option1 -> 0
            R.id.rb_option2 -> 1
            R.id.rb_option3 -> 2
            R.id.rb_option4 -> 3
            else -> -1
        }
    }

    private fun reset() {
        userAnswers.clear()
        initQuestionsAdapter()
    }

    private fun submit() {
        val correctAnswers: Int = checkAnswers()
        // show Alert Dialog with correct answers
        AlertDialog.Builder(this)
            .setTitle("Result")
            .setMessage("Congratulations! You Submitted on ${formattedDateTime()}, you achieved ${getPercentage(correctAnswers, questions.size)}%")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }

    private fun getPercentage(correctAnswers: Int, size: Int): String {
        return ((correctAnswers.toDouble() / size.toDouble()) * 100).toString()
    }

    private fun checkAnswers(): Int {
        var correctAnswers= 0
        for(question in questions) {
            if(userAnswers[question.id]?.sorted() == realAnswers[question.id]?.sorted()) {
                correctAnswers++
            }
        }

        return correctAnswers
    }

    private fun formattedDateTime(): String {
        return SimpleDateFormat("dd MMMM yyyy HH:mm").format(System.currentTimeMillis())
    }

}