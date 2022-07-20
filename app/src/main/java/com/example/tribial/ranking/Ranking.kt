package com.example.tribial.ranking

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.tribial.database.QuestionDao
import com.example.tribial.ranking.Ranking.Input
import com.example.tribial.ranking.Ranking.Output

interface Ranking : Rib, Connectable<Input, Output> {

    interface Dependency {
        val localDataSource: QuestionDao
    }

    sealed class Input

    sealed class Output

    class Customisation(
        val viewFactory: RankingView.Factory = RankingViewImpl.Factory()
    ) : RibCustomisation

    // Workflow
    // todo: rename rather than delete, and add more
    // todo: expose all meaningful operations
    //fun businessLogicOperation(): Single<Ranking>

    // todo: expose all possible children (even permanent parts), or remove if there's none
    // fun attachChild1(): Single<Child>
}
