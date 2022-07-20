package com.example.tribial.app_root

import android.util.Log
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.push
import com.example.tribial.app_root.AppRoot.Input
import com.example.tribial.app_root.AppRoot.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode
import com.example.tribial.add.Add
import com.example.tribial.app_root.routing.AppRootRouter
import com.example.tribial.menu.Menu
import com.example.tribial.play.Play
import com.example.tribial.ranking.Ranking
import io.reactivex.Single

class AppRootNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<AppRootView>?,
    private val backStack: BackStack<AppRootRouter.Configuration>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : RxWorkflowNode<AppRootView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), AppRoot, Connectable<Input, Output> by connector {

    /*
    override fun businessLogicOperation(): Single<AppRoot> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }
     */

    /*
    override fun attachMenu(): Single<Menu> =
        attachWorkflow {
            Log.d("WORKFLOW", "AppRoot / attachMenu")
            backStack.push(AppRootRouter.Configuration.Menu)
        }

    override fun attachPlay(username: String): Single<Play> =
        attachWorkflow {
            Log.d("WORKFLOW", "AppRoot / attachPlay")
            backStack.push(AppRootRouter.Configuration.Play(username))
        }


    override fun attachAdd(): Single<Add> =
        attachWorkflow {
            Log.d("WORKFLOW", "AppRoot / attachAdd")
            backStack.push(AppRootRouter.Configuration.Add)
        }


    override fun attachRanking(): Single<Ranking> =
        attachWorkflow {
            Log.d("WORKFLOW", "AppRoot / attachRanking")
            backStack.push(AppRootRouter.Configuration.Ranking)
    }

     */


}
