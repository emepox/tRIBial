package com.example.tribial.app_root

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.CrossFader
import com.badoo.ribs.routing.transition.handler.Slider
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.tribial.api.ApiDataSource
import com.example.tribial.app_root.AppRoot.Input
import com.example.tribial.app_root.AppRoot.Output
import com.example.tribial.app_root.routing.AppRootRouter
import com.example.tribial.database.QuestionDao

interface AppRoot : Rib, Connectable<Input, Output> {

    interface Dependency {
        val localDataSource: QuestionDao
        val remoteDataSource: ApiDataSource
    }

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: AppRootView.Factory = AppRootViewImpl.Factory(),
        val transitionHandler: TransitionHandler<AppRootRouter.Configuration>? = TransitionHandler.multiple(
            CrossFader {
                    it.configuration is AppRootRouter.Configuration.Play ||
                    it.configuration is AppRootRouter.Configuration.Ranking ||
                    it.configuration is AppRootRouter.Configuration.Add
            }
        )
    ) : RibCustomisation

}
