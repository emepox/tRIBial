package com.example.tribial.add

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.tribial.add.Add.Input
import com.example.tribial.add.Add.Output
import com.example.tribial.database.QuestionDao
import io.reactivex.Single

interface Add : Rib, Connectable<Input, Output> {

    interface Dependency {
        val localDataSource: QuestionDao
    }

    sealed class Input

    sealed class Output {
        object QuestionAdded: Output()
    }

    class Customisation(
        val viewFactory: AddView.Factory = AddViewImpl.Factory()
    ) : RibCustomisation

    // Workflow
    // todo: rename rather than delete, and add more
    // todo: expose all meaningful operations
    fun businessLogicOperation(): Single<Add>

    // todo: expose all possible children (even permanent parts), or remove if there's none
    // fun attachChild1(): Single<Child>
}
