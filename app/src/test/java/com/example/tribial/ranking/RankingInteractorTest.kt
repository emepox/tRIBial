package com.example.tribial.ranking

import com.badoo.ribs.test.emptyBuildParams
import org.junit.After
import org.junit.Before
import org.junit.Test

class RankingInteractorTest {

    private lateinit var interactor: RankingInteractor

    @Before
    fun setup() {
        interactor = RankingInteractor(
            buildParams = emptyBuildParams(),
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
