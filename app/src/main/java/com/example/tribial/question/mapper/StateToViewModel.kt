package com.example.tribial.question.mapper

import com.example.tribial.question.QuestionView
import com.example.tribial.question.feature.QuestionFeature

internal object StateToViewModel: (QuestionFeature.State) -> QuestionView.ViewModel {
    override fun invoke(state: QuestionFeature.State): QuestionView.ViewModel =
        with(state) {
            val answers = listOf(answer1, answer2, answer3, answer4).shuffled()
            QuestionView.ViewModel(
                question = question,
                answer1 = answers[0],
                answer2 = answers[1],
                answer3 = answers[2],
                answer4 = answers[3]
            )
        }
}