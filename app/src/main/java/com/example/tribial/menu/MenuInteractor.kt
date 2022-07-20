package com.example.tribial.menu

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.example.tribial.menu.mapper.ViewEventToOutput

internal class MenuInteractor(
    buildParams: BuildParams<*>,
    //private val feature: MenuFeature
) : Interactor<Menu, MenuView>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            //bind(feature.news to rib.output using NewsToOutput)
            //bind(rib.input to feature using InputToWish)

        }


    }



    override fun onViewCreated(view: MenuView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
           bind(view to rib.output using ViewEventToOutput)

        // bind(view to feature using ViewEventToWish)
            //bind(view to MenuAnalytics using ViewEventToAnalyticsEvent)
        }
    }
}
