package com.example.tribial.play

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.routing.source.backstack.BackStack
import com.example.tribial.play.feature.PlayFeature
import com.badoo.ribs.test.emptyBuildParams
import com.badoo.ribs.test.interactor.RibInteractorTestHelper
import com.badoo.ribs.test.view.RibViewStub
import com.example.tribial.play.routing.PlayRouter
import io.reactivex.Observer
import org.junit.Test
import org.mockito.kotlin.mock


class PlayInteractorTest {

    private val feature: PlayFeature = mock()
    private val backStack = BackStack<PlayRouter.Configuration>(PlayRouter.Configuration.Default, emptyBuildParams())
    private val interactor = PlayInteractor(
        buildParams = emptyBuildParams(),
        feature = feature,
        backStack = backStack
    )

    private val view = object : RibViewStub<PlayView.ViewModel, PlayView.Event>(), PlayView {
        override fun subscribe(observer: Observer<in PlayView.Event>) {
            TODO("Not yet implemented")
        }

        override fun accept(t: PlayView.ViewModel?) {
            TODO("Not yet implemented")
        }
    }

    private val interactorTestHelper = RibInteractorTestHelper(
        interactor = interactor,
        ribFactory = { PlayNode(it, viewFactory = { view }, plugins = emptyList(), backStack = backStack) }
    )

    @Test
    fun `WHEN open child button is clicked THEN back stack has Child1 configuration`() {
        interactorTestHelper.moveToStateAndCheck(Lifecycle.State.CREATED) {
        }

    }
}
