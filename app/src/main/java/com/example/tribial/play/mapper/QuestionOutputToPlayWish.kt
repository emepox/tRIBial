package com.example.tribial.play.mapper

import com.example.tribial.play.feature.PlayFeature
import com.example.tribial.question.Question

internal object QuestionOutputToPlayWish: (Question.Output) -> PlayFeature.Wish {
    override fun invoke(output: Question.Output): PlayFeature.Wish =
        when (output) {
            is Question.Output.AnswerSent -> PlayFeature.Wish.CheckAnswerProvided(output.answer)
        }
}

