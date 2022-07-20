package com.example.tribial.play

import com.example.tribial.play.feature.PlayFeature
import com.badoo.ribs.test.emptyBuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class PlayInteractorTest {

    private val feature: PlayFeature = mock()
    private lateinit var interactor: PlayInteractor

    @Before
    fun setup() {
        interactor = PlayInteractor(
            buildParams = emptyBuildParams(),
            feature = feature
        )
    }

    @After
    fun tearDown() {
    }

    /**
     * TODO: Add real tests.
     */
    @Test
    fun `an example test with some conditions should pass`() {
        throw RuntimeException("Add real tests.")
    }
}
