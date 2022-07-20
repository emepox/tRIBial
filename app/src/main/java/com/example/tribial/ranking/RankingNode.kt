package com.example.tribial.ranking

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.ranking.Ranking.Input
import com.example.tribial.ranking.Ranking.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode
import io.reactivex.Single

class RankingNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<RankingView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : RxWorkflowNode<RankingView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Ranking, Connectable<Input, Output> by connector {

    /*
    override fun businessLogicOperation(): Single<Ranking> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }

     */
}
