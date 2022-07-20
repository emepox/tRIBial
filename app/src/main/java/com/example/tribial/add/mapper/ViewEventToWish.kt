package com.example.tribial.add.mapper

import com.example.tribial.add.AddView.Event
import com.example.tribial.add.feature.AddFeature.Wish

internal object ViewEventToWish : (Event) -> Wish? {

    override fun invoke(event: Event): Wish? =
        TODO("Implement AddViewEventToWish mapping")
}
