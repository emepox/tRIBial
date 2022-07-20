package com.example.tribial.app_root

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.childaware.childAware
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.push
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.example.tribial.app_root.routing.AppRootRouter
import com.example.tribial.menu.Menu
import com.example.tribial.play.Play
import io.reactivex.functions.Consumer

internal class AppRootInteractor(
    buildParams: BuildParams<*>,
    private val backStack: BackStack<AppRootRouter.Configuration>
) : Interactor<AppRoot, AppRootView>(
    buildParams = buildParams
) {

    private val menuOutputConsumer : Consumer<Menu.Output> = Consumer {
        when(it) {
            is Menu.Output.RequestToPlay -> backStack.push(AppRootRouter.Configuration.Play(it.username))
            is Menu.Output.RequestToAdd -> backStack.push(AppRootRouter.Configuration.Add)
            is Menu.Output.RequestToRanking -> backStack.push(AppRootRouter.Configuration.Ranking)
        }
    }

    private val playOutputConsumer: Consumer<Play.Output> = Consumer {
        when (it) {
            is Play.Output.GameFinished -> backStack.replace(AppRootRouter.Configuration.Ranking)
        }
    }

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            //bind(feature.news to rib.output using NewsToOutput)
            //bind(rib.input to feature using InputToWish)
        }
    }

    override fun onViewCreated(view: AppRootView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
        }
        childAware(viewLifecycle) {
            whenChildAttached<Menu> { lifecycle, child ->
                lifecycle.startStop {
                    bind(child.output to menuOutputConsumer)
                }
                whenChildAttached<Play> { lifecycle, child ->
                    lifecycle.startStop {
                        bind(child.output to playOutputConsumer)
                    }

                }

            }
        }
    }
}
