package com.example.tribial.app_root

import com.example.tribial.app_root.feature.AppRootFeature
import com.badoo.ribs.test.emptyBuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppRootInteractorTest {

    private val feature: AppRootFeature = mock()
    private lateinit var interactor: AppRootInteractor

    @Before
    fun setup() {
        interactor = AppRootInteractor(
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
