package com.example.tribial.menu

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.menu.Menu.Input
import com.example.tribial.menu.Menu.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode
import io.reactivex.Single

class MenuNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<MenuView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : RxWorkflowNode<MenuView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Menu, Connectable<Input, Output> by connector {

    /*
    override fun businessLogicOperation(): Single<Menu> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }

     */
}
