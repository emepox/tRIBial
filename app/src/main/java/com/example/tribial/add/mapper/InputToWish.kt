package com.example.tribial.add.mapper

import com.example.tribial.add.Add.Input
import com.example.tribial.add.feature.AddFeature.Wish

internal object InputToWish : (Input) -> Wish? {

    override fun invoke(event: Input): Wish? =
        TODO("Implement AddInputToWish mapping")
}
