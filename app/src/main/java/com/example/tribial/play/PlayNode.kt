package com.example.tribial.play

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.routing.source.backstack.BackStack
import com.example.tribial.play.Play.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode
import com.example.tribial.play.routing.PlayRouter

class PlayNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<PlayView>?,
    private val backStack: BackStack<PlayRouter.Configuration>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Nothing, Output> = NodeConnector()
) : RxWorkflowNode<PlayView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Play, Connectable<Nothing, Output> by connector {

    /*
    override fun businessLogicOperation(): Single<Play> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }

     */
}
