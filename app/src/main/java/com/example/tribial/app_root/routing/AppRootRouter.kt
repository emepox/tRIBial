package com.example.tribial.app_root.routing

import android.os.Parcelable
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.CrossFader
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.tribial.app_root.AppRoot
import com.example.tribial.play.Play
import com.example.tribial.play.PlayBuilder
import kotlinx.parcelize.Parcelize

class AppRootRouter(
    private val buildParams: BuildParams<*>,
    routingSource: RoutingSource<Configuration>,
    private val builders: AppRootChildBuilders,
    transitionHandler: TransitionHandler<Configuration>? = CrossFader()
) : Router<AppRootRouter.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
){
    sealed class Configuration : Parcelable {
            @Parcelize data class Play(val username: String): Configuration()
            @Parcelize object Add: Configuration()
            @Parcelize object Ranking: Configuration()
            @Parcelize object Menu: Configuration()
    }

    override fun resolve(routing: Routing<Configuration>): Resolution =
        with(builders) {
            when(val configuration = routing.configuration) {
                is Configuration.Play -> child {play.build(it, Play.Params(configuration.username))}
                is Configuration.Add -> child {add.build(it)}
                is Configuration.Ranking -> child { ranking.build(it) }
                is Configuration.Menu -> child { menu.build(it) }
            }
        }
}