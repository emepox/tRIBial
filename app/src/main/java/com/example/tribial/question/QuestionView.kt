package com.example.tribial.question

import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.R
import com.example.tribial.question.QuestionView.Event
import com.example.tribial.question.QuestionView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface QuestionView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event {
        data class NextAnswerClicked(val answer: String): Event()
    }

    data class ViewModel(
        val question: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val answer4: String,
    )

    fun interface Factory : ViewFactoryBuilder<Nothing?, QuestionView>
}


class QuestionViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    QuestionView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    private val radioGroup: RadioGroup = findViewById(R.id.rg_answers_container)
    private val nextButton: Button = findViewById(R.id.btn_answer)
    private val question: TextView = findViewById(R.id.tv_question)
    private val answer1: RadioButton = findViewById(R.id.radio_button_1)
    private val answer2: RadioButton = findViewById(R.id.radio_button_2)
    private val answer3: RadioButton = findViewById(R.id.radio_button_3)
    private val answer4: RadioButton = findViewById(R.id.radio_button_4)


    init {
        setListeners()
    }

    private fun setListeners() {
        nextButton.setOnClickListener {
            val answer = (radioGroup.findViewById(radioGroup.checkedRadioButtonId) as RadioButton).text.toString()
            events.accept(Event.NextAnswerClicked(answer))
        }
    }

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_question
    ) : QuestionView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<QuestionView> =
            ViewFactory {
                QuestionViewImpl(
                    it.inflate(layoutRes)
                )
            }
    }

    override fun accept(vm: QuestionView.ViewModel) {
        question.text = vm.question
        answer1.text = vm.answer1
        answer2.text = vm.answer2
        answer3.text = vm.answer3
        answer4.text = vm.answer4
    }
}
