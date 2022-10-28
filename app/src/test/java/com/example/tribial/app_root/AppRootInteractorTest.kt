package com.example.tribial.app_root

import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.test.emptyBuildParams
import com.example.tribial.app_root.routing.AppRootRouter
import org.junit.After
import org.junit.Before
import org.junit.Test

class AppRootInteractorTest {

    private lateinit var interactor: AppRootInteractor
    private val backStack = BackStack<AppRootRouter.Configuration>(AppRootRouter.Configuration.Menu, emptyBuildParams())

    @Before
    fun setup() {
        interactor = AppRootInteractor(
            buildParams = emptyBuildParams(),
            backStack = backStack,
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
