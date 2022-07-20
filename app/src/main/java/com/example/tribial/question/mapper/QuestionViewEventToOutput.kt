package com.example.tribial.question.mapper

import android.util.Log
import com.example.tribial.question.Question
import com.example.tribial.question.QuestionView

internal object QuestionViewEventToOutput : (QuestionView.Event) -> Question.Output {
    override fun invoke(event: QuestionView.Event): Question.Output =
        when(event) {
            is QuestionView.Event.NextAnswerClicked -> Question.Output.AnswerSent(event.answer)
        }

}