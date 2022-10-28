package com.example.tribial.play

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.disposables
import com.example.tribial.play.datasource.DummyRepository
import com.example.tribial.play.datasource.QuestionRepository
import com.example.tribial.play.feature.PlayFeature
import com.example.tribial.play.routing.PlayChildBuilders
import com.example.tribial.play.routing.PlayRouter

class PlayBuilder(
    private val dependency: Play.Dependency
) : Builder<Play.Params, Play>() {

    override fun build(buildParams: BuildParams<Play.Params>): Play {
        val builders = PlayChildBuilders(dependency)
        val customisation = buildParams.getOrDefault(Play.Customisation())
        // Real repository. Won't be used here
        //val repository = QuestionRepositoryImpl(dependency.questionDao,dependency.remoteDataSource)
        // Fake repo:
        val repository = DummyRepository()
        val backStack = backStack(buildParams)
        val feature = feature(buildParams, repository)
        val interactor = interactor(buildParams, feature, backStack)

        /*
        val viewDependency = object : PlayView.ViewDependency {
            override val remoteDataSource: ApiDataSource = dependency.remoteDataSource
            override val localDataSource: RoomDataSource = dependency.localDataSource

        }
         */

        val router = router(
            buildParams = buildParams,
            routingSource = backStack,
            builders = builders,
            transitionHandler = customisation.transitionHandler
        )

        return node(
            buildParams,
            customisation,
            feature,
            interactor,
            //viewDependency,
            backStack,
            router

        )
    }

    private fun backStack(buildParams: BuildParams<*>) =
        BackStack<PlayRouter.Configuration>(
            buildParams = buildParams,
            initialConfiguration = PlayRouter.Configuration.Default
        // TODO() Comprobar que esto funciona
        )

    private fun feature(
        buildParams: BuildParams<Play.Params>,
        repository: QuestionRepository
    ) =
        PlayFeature(buildParams.payload.username, repository)

    private fun interactor(
        buildParams: BuildParams<*>,
        feature: PlayFeature,
        backStack: BackStack<PlayRouter.Configuration>
    ) = PlayInteractor(
        buildParams = buildParams,
        feature = feature,
        backStack = backStack
    )

    private fun router(
        buildParams: BuildParams<*>,
        routingSource: RoutingSource<PlayRouter.Configuration>,
        builders: PlayChildBuilders,
        transitionHandler: TransitionHandler<PlayRouter.Configuration>? = null
    ) = PlayRouter(
        buildParams = buildParams,
        builders = builders,
        routingSource = routingSource,
        transitionHandler = transitionHandler
    )

    private fun node(
        buildParams: BuildParams<Play.Params>,
        customisation: Play.Customisation,
        feature: PlayFeature,
        interactor: PlayInteractor,
        //viewDependency: PlayView.ViewDependency,
        backStack: BackStack<PlayRouter.Configuration>,
        router: PlayRouter
    ) = PlayNode(
        buildParams = buildParams,
        viewFactory = customisation.viewFactory(null),
        plugins = listOf(interactor, router, disposables(feature)),
        backStack = backStack
    )

}
