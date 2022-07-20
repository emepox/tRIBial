package com.example.tribial.add

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.add.Add.Input
import com.example.tribial.add.Add.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode
import io.reactivex.Single

class AddNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<AddView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : RxWorkflowNode<AddView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Add, Connectable<Input, Output> by connector {

    override fun businessLogicOperation(): Single<Add> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }
}
