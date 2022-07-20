package com.example.tribial.add

import com.example.tribial.add.feature.AddFeature
import com.badoo.ribs.test.emptyBuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class AddInteractorTest {

    private val feature: AddFeature = mock()
    private lateinit var interactor: AddInteractor

    @Before
    fun setup() {
        interactor = AddInteractor(
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
