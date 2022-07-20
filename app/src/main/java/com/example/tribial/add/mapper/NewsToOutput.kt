package com.example.tribial.add.mapper

import com.example.tribial.add.Add.Output
import com.example.tribial.add.feature.AddFeature.News

internal object NewsToOutput : (News) -> Output? {

    override fun invoke(news: News): Output? =
        TODO("Implement AddNewsToOutput mapping")
}
