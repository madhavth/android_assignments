package com.example.assignment5

data class Question(
    val id: Int,
    val question: String,
    val answers: List<Int>,
    val options: List<String>,
    val questionType: QuestionType,
    val point: Int = 50
)

enum class QuestionType {
    CHECKBOX, RADIO
}