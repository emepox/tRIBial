package com.example.tribial.play.routing

import android.os.Parcelable
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.resolution.Resolution.Companion.noop
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.tribial.question.Question
import kotlinx.parcelize.Parcelize

class PlayRouter(
    private val buildParams: BuildParams<*>,
    routingSource: RoutingSource<Configuration>,
    private val builders: PlayChildBuilders,
    transitionHandler: TransitionHandler<Configuration>? = null,
    ): Router<PlayRouter.Configuration>(
    buildParams,
    routingSource,
    transitionHandler = transitionHandler

) {

    sealed class Configuration: Parcelable {
        @Parcelize data class Question(val questionId: Int): Configuration()
        @Parcelize object Default: Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): Resolution =
        with(builders) {
            when(val configuration = routing.configuration) {
                is Configuration.Question -> child {
                    question.build(
                        it,
                        Question.Params(configuration.questionId)
                    )
                }
                is Configuration.Default -> noop()
            }
        }
}