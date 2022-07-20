package com.example.tribial.play.datasource.models

data class Question(
    val questionId: Int,
    val question: String,
    val correctAnswer: String,
    val wrongAnswer: List<String>,
)