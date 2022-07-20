package com.example.tribial.add

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.rx2.disposables
import com.example.tribial.add.feature.AddFeature

class AddBuilder(
    private val dependency: Add.Dependency
) : SimpleBuilder<Add>() {

    override fun build(buildParams: BuildParams<Nothing?>): Add {
        val customisation = buildParams.getOrDefault(Add.Customisation())
        val feature = feature()
        val interactor = interactor(buildParams, feature)

        return node(buildParams, customisation, feature, interactor)
    }

    private fun feature() =
        AddFeature()

    private fun interactor(
        buildParams: BuildParams<*>,
        feature: AddFeature
    ) = AddInteractor(
            buildParams = buildParams,
            feature = feature
        )

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Add.Customisation,
        feature: AddFeature,
        interactor: AddInteractor
    ) = AddNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor, disposables(feature))
        )
}
