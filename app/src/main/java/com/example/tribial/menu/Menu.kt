package com.example.tribial.menu

import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.example.tribial.menu.Menu.Input
import com.example.tribial.menu.Menu.Output
import com.example.tribial.ranking.RankingView
import com.example.tribial.ranking.RankingViewImpl
import io.reactivex.Single

interface Menu : Rib, Connectable<Input, Output> {

    interface Dependency

    sealed class Input

    sealed class Output {
        data class RequestToPlay(val username: String): Output()
        object RequestToAdd: Output()
        object RequestToRanking: Output()
    }

    class Customisation(
        val viewFactory: MenuView.Factory = MenuViewImpl.Factory()
    ) : RibCustomisation

    // Workflow
    // todo: rename rather than delete, and add more
    // todo: expose all meaningful operations
    //fun businessLogicOperation(): Single<Menu>

    // todo: expose all possible children (even permanent parts), or remove if there's none
    // fun attachChild1(): Single<Child>
}
