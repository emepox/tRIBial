package com.example.tribial.app_root.routing

import com.example.tribial.add.Add
import com.example.tribial.add.AddBuilder
import com.example.tribial.app_root.AppRoot
import com.example.tribial.menu.Menu
import com.example.tribial.menu.MenuBuilder
import com.example.tribial.play.Play
import com.example.tribial.play.PlayBuilder
import com.example.tribial.ranking.Ranking
import com.example.tribial.ranking.RankingBuilder


class AppRootChildBuilders(
    dependency: AppRoot.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val add = AddBuilder(subtreeDeps)
    val play = PlayBuilder(subtreeDeps)
    val ranking = RankingBuilder(subtreeDeps)
    val menu = MenuBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: AppRoot.Dependency
    ) : AppRoot.Dependency by dependency,
        Play.Dependency,
        Add.Dependency,
        Ranking.Dependency,
        Menu.Dependency
}