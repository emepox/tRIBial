package com.example.tribial.menu.mapper

import com.example.tribial.menu.Menu
import com.example.tribial.menu.MenuView

internal object ViewEventToOutput : (MenuView.Event) -> Menu.Output {
    override fun invoke(event: MenuView.Event): Menu.Output =
        when(event) {
            is MenuView.Event.PlayButtonClicked -> Menu.Output.RequestToPlay(event.username)
            is MenuView.Event.AddButtonClicked -> Menu.Output.RequestToAdd
            is MenuView.Event.RankingButtonClicked -> Menu.Output.RequestToRanking
        }
}

