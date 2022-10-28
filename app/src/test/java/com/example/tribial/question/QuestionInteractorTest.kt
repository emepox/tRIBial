package com.example.tribial.question

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.test.emptyBuildParams
import com.badoo.ribs.test.interactor.RibInteractorTestHelper
import com.badoo.ribs.test.view.RibViewStub
import com.example.tribial.play.PlayInteractor
import com.example.tribial.play.PlayNode
import com.example.tribial.play.PlayView
import com.example.tribial.play.feature.PlayFeature
import com.example.tribial.play.routing.PlayRouter
import com.example.tribial.question.feature.QuestionFeature
import io.reactivex.Observer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class QuestionInteractorTest {
/*
    private val feature: QuestionFeature = mock()
    private val interactor = QuestionInteractor(
        feature = feature,
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
        ribFactory = { QuestionNode(it, viewFactory = { view }, plugins = emptyList()) }
    )

    @Test
    fun `WHEN open child button is clicked THEN back stack has Child1 configuration`() {
        interactorTestHelper.moveToStateAndCheck(Lifecycle.State.CREATED) {
        }

    }

 */
}
