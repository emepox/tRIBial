package com.example.tribial.question

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.tribial.question.Question.Input
import com.example.tribial.question.Question.Output
import io.reactivex.Single

interface Question : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output {
        data class AnswerSent(val answer: String): Output()
    }

    data class Params(
        val questionId: Int
    )

    class Customisation(
        val viewFactory: QuestionView.Factory = QuestionViewImpl.Factory()
    ) : RibCustomisation

}
