package com.example.tribial.play

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.routing.transition.*
import com.badoo.ribs.routing.transition.effect.Gravity
import com.badoo.ribs.routing.transition.effect.helper.AnimationContainer
import com.badoo.ribs.routing.transition.effect.slide.slide
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.tribial.api.ApiDataSource
import com.example.tribial.play.Play.Output
import com.example.tribial.database.QuestionDao
import com.example.tribial.play.routing.PlayRouter

interface Play : Rib, Connectable<Nothing, Output> {

    interface Dependency {
        val remoteDataSource: ApiDataSource
        val localDataSource: QuestionDao
    }

    sealed class Output {
        object GameFinished: Output()
    }

    class Customisation(
        val viewFactory: ViewFactoryBuilder<Nothing?, PlayView> = PlayViewImpl.Factory(),
        val transitionHandler: TransitionHandler<PlayRouter.Configuration>? = TransitionHandler.multiple(
            Slider2 {
                it.configuration is PlayRouter.Configuration.Question
            }
        )
    ) : RibCustomisation

    data class Params(
        val username: String
    )
}

open class Slider2<T>(
    private val gravity: Gravity = Gravity.LEFT,
    private val animationContainer: AnimationContainer = AnimationContainer.RootView,
    private val duration: Long = 300,
    private val interpolator: Interpolator = AccelerateDecelerateInterpolator(),
    private val condition: (TransitionElement<out T>) -> Boolean = { true }
) : TransitionHandler<T> {

    override fun onTransition(elements: List<TransitionElement<out T>>): TransitionPair {
        val exit = elements.filter { it.direction == TransitionDirection.EXIT && condition(it) }
        val enter = elements.filter { it.direction == TransitionDirection.ENTER && condition(it)}

        return TransitionPair(
            exiting = Transition.multiple(
                exit { slide(Gravity.RIGHT, animationContainer, duration, interpolator) }
            ),
            entering = Transition.multiple(
                enter { slide(Gravity.RIGHT, animationContainer, duration, interpolator) }
            )
        )
    }
}


