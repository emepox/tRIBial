package com.example.tribial.question

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.rx2.disposables
import com.example.tribial.play.datasource.DummyRepository
import com.example.tribial.play.datasource.QuestionRepository
import com.example.tribial.question.feature.QuestionFeature

class QuestionBuilder(
    private val dependency: Question.Dependency
) : Builder<Question.Params, Question>() {

    override fun build(buildParams: BuildParams<Question.Params>): Question {
        val customisation = buildParams.getOrDefault(Question.Customisation())
        val feature = feature(buildParams.payload, DummyRepository())
        val interactor = interactor(buildParams, feature)
        return node(buildParams, customisation, feature, interactor)
    }


    private fun feature(
        buildParams: Question.Params,
        repository: QuestionRepository
    ) = QuestionFeature(
        questionId = buildParams.questionId,
        repository = repository

    )

    private fun interactor(
        buildParams: BuildParams<Question.Params>,
        feature: QuestionFeature
    ) = QuestionInteractor(
            buildParams = buildParams,
            feature = feature
        )

    private fun node(
        buildParams: BuildParams<Question.Params>,
        customisation: Question.Customisation,
        feature: QuestionFeature,
        interactor: QuestionInteractor
    ) = QuestionNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor, disposables(feature))
        )
}


