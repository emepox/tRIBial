package com.example.tribial.menu

import com.example.tribial.menu.feature.MenuFeature
import com.badoo.ribs.test.emptyBuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class MenuInteractorTest {

    private val feature: MenuFeature = mock()
    private lateinit var interactor: MenuInteractor

    @Before
    fun setup() {
        interactor = MenuInteractor(
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
