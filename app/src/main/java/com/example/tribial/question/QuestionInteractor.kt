package com.example.tribial.question

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.example.tribial.question.feature.QuestionFeature
import com.example.tribial.question.mapper.QuestionViewEventToOutput
import com.example.tribial.question.mapper.StateToViewModel


internal class QuestionInteractor(
    buildParams: BuildParams<Question.Params>,
    private val feature: QuestionFeature
) : Interactor<Question, QuestionView>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
           // bind(feature.news to rib.output using NewsToOutput)
           // bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: QuestionView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
        bind(view to rib.output using QuestionViewEventToOutput)
        bind(feature to view using StateToViewModel)
        }
    }
}
