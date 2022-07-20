package com.example.tribial.add.mapper

import com.example.tribial.add.AddView.ViewModel
import com.example.tribial.add.feature.AddFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
        TODO("Implement StateToViewModel mapping")
}
