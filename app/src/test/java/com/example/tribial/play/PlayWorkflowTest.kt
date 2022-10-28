package com.example.tribial.play

import org.junit.After
import org.junit.Before
import org.junit.Test

class PlayWorkflowTest {

    private lateinit var workflow: Play

    @Before
    /*
    fun setup() {
        workflow = PlayBuilder(object : Play.Dependency {
            override val remoteDataSource: ApiDataSource
                get() = TODO("Not yet implemented")
            override val localDataSource: QuestionDao
                get() = TODO("Not yet implemented")
        }).build(BuildContext.root(savedInstanceState = null)).also {
            it.node.onCreate()
        }
    }

     */

    @After
    fun tearDown() {
    }

    /**
     * TODO: Add tests for every workflow action that operates on the node
     */
    @Test
    fun `business logic operation test`() {
        //workflow.businessLogicOperation()
        // verify(feature).accept(Wish)

        throw RuntimeException("Add real tests.")
    }
}
