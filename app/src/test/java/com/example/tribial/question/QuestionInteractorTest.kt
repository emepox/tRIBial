package com.example.tribial.question

import com.example.tribial.question.feature.QuestionFeature
import com.badoo.ribs.test.emptyBuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuestionInteractorTest {

    private val feature: QuestionFeature = mock()
    private lateinit var interactor: QuestionInteractor

    @Before
    fun setup() {
        interactor = QuestionInteractor(
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
