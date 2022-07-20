package com.example.tribial.menu

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.rx2.disposables

class MenuBuilder(
    private val dependency: Menu.Dependency
) : SimpleBuilder<Menu>() {

    override fun build(buildParams: BuildParams<Nothing?>): Menu {
        val customisation = buildParams.getOrDefault(Menu.Customisation())
        //val feature = feature()
        val interactor = interactor(buildParams)

        return node(buildParams, customisation, interactor)
    }

    /*
    private fun feature() =
        MenuFeature()

     */
    private fun interactor(
        buildParams: BuildParams<*>,
        //feature: MenuFeature
    ) = MenuInteractor(
            buildParams = buildParams,
            //feature = feature
        )

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Menu.Customisation,
        //feature: MenuFeature,
        interactor: MenuInteractor
    ) = MenuNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor, disposables())
        )
}
