package com.example.tribial.play.mapper

import com.example.tribial.play.Play
import com.example.tribial.play.feature.PlayFeature

object PlayNewsToOutput: (PlayFeature.News) -> Play.Output? {
    override fun invoke(news: PlayFeature.News): Play.Output? =
        when (news){
            is PlayFeature.News.GameFinished -> Play.Output.GameFinished

            else -> {null}
        }
}