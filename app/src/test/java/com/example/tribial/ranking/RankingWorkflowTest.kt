package com.example.tribial.ranking

import com.badoo.ribs.core.modality.BuildContext
import com.example.tribial.database.QuestionDao
import org.junit.After
import org.junit.Before
import org.junit.Test

class RankingWorkflowTest {

    private lateinit var workflow: Ranking

    @Before
    fun setup() {
        workflow = RankingBuilder(object : Ranking.Dependency {
            override val localDataSource: QuestionDao
                get() = TODO("Not yet implemented")
        }).build(BuildContext.root(savedInstanceState = null)).also {
            it.node.onCreate()
        }
    }

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
