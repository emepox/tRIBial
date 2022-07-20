package com.example.tribial.ranking

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams

internal class RankingInteractor(
    buildParams: BuildParams<*>,
    //private val feature: RankingFeature
) : Interactor<Ranking, RankingView>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
           // bind(feature.news to rib.output using NewsToOutput)
           // bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: RankingView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            //bind(feature to view using StateToViewModel)
            //bind(view to feature using ViewEventToWish)
            //bind(view to RankingAnalytics using ViewEventToAnalyticsEvent)
        }
    }
}
