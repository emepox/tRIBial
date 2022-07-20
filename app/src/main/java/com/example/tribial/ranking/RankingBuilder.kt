package com.example.tribial.ranking

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.rx2.disposables

class RankingBuilder(
    private val dependency: Ranking.Dependency
) : SimpleBuilder<Ranking>() {

    override fun build(buildParams: BuildParams<Nothing?>): Ranking {
        val customisation = buildParams.getOrDefault(Ranking.Customisation())
        //val feature = feature()
        val interactor = interactor(buildParams)

        return node(buildParams, customisation, interactor)
    }

    /*
    private fun feature() =
        RankingFeature()
     */

    private fun interactor(
        buildParams: BuildParams<*>,
        //feature: RankingFeature
    ) = RankingInteractor(
            buildParams = buildParams,
            //feature = feature
        )

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: Ranking.Customisation,
       // feature: RankingFeature,
        interactor: RankingInteractor
    ) = RankingNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor, disposables())
        )
}
