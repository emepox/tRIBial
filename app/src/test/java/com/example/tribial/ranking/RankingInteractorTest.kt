package com.example.tribial.ranking

import com.example.tribial.ranking.feature.RankingFeature
import com.badoo.ribs.test.emptyBuildParams
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

class RankingInteractorTest {

    private val feature: RankingFeature = mock()
    private lateinit var interactor: RankingInteractor

    @Before
    fun setup() {
        interactor = RankingInteractor(
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
