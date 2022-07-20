package com.example.tribial.app_root

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.tribial.app_root.routing.AppRootChildBuilders
import com.example.tribial.app_root.routing.AppRootRouter

class AppRootBuilder(
    private val dependency: AppRoot.Dependency
) : SimpleBuilder<AppRoot>() {

    override fun build(buildParams: BuildParams<Nothing?>): AppRoot {
        val builders = AppRootChildBuilders(dependency)
        val customisation = buildParams.getOrDefault(AppRoot.Customisation())
        val backStack = backStack(buildParams)
        val interactor = interactor(
            buildParams, backStack
        )

        val router = router(
            buildParams = buildParams,
            routingSource = backStack,
            builders = builders,
            transitionHandler = customisation.transitionHandler
        )

        return node(
            buildParams = buildParams,
            customisation = customisation,
            interactor = interactor,
            router = router,
            backStack = backStack
        )
    }

    private fun backStack(buildParams: BuildParams<*>) =
        BackStack<AppRootRouter.Configuration>(
            buildParams = buildParams,
            initialConfiguration = AppRootRouter.Configuration.Menu
        )

    private fun interactor(
        buildParams: BuildParams<*>,
        backStack: BackStack<AppRootRouter.Configuration>
    ) = AppRootInteractor(
            buildParams = buildParams,
            backStack = backStack
        )

    private fun router(
        buildParams: BuildParams<*>,
        routingSource: RoutingSource<AppRootRouter.Configuration>,
        builders: AppRootChildBuilders,
        transitionHandler: TransitionHandler<AppRootRouter.Configuration>? = null,
    ) = AppRootRouter(
        buildParams = buildParams,
        builders = builders,
        routingSource = routingSource,
        transitionHandler = transitionHandler
    )

    private fun node(
        buildParams: BuildParams<Nothing?>,
        customisation: AppRoot.Customisation,
        interactor: AppRootInteractor,
        backStack: BackStack<AppRootRouter.Configuration>,
        router: AppRootRouter
    ) = AppRootNode(
            buildParams = buildParams,
            viewFactory = customisation.viewFactory(null),
            plugins = listOf(interactor, router),
            backStack = backStack,
        )



}
