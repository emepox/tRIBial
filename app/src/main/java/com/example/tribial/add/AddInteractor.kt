package com.example.tribial.add

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.binder.using
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.example.tribial.add.feature.AddFeature
import com.example.tribial.add.mapper.InputToWish
import com.example.tribial.add.mapper.NewsToOutput
import com.example.tribial.add.mapper.StateToViewModel
import com.example.tribial.add.mapper.ViewEventToWish

internal class AddInteractor(
    buildParams: BuildParams<*>,
    private val feature: AddFeature
) : Interactor<Add, AddView>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            //bind(feature.news to rib.output using NewsToOutput)
           // bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: AddView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            //bind(feature to view using StateToViewModel)
            //bind(view to feature using ViewEventToWish)
            // bind(view to AddAnalytics using ViewEventToAnalyticsEvent)
        }
    }
}
