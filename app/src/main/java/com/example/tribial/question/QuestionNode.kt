package com.example.tribial.question

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.question.Question.Input
import com.example.tribial.question.Question.Output
import com.badoo.ribs.rx2.workflows.RxWorkflowNode
import io.reactivex.Single

class QuestionNode internal constructor(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<QuestionView>?,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) : RxWorkflowNode<QuestionView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), Question, Connectable<Input, Output> by connector {

    /*
    override fun businessLogicOperation(): Single<Question> =
        executeWorkflow {
            // todo e.g. push wish to feature / trigger input / output
            // feature.accept()
        }

     */
}
