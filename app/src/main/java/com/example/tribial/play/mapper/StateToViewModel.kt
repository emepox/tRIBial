package com.example.tribial.play.mapper

import com.example.tribial.play.PlayView.ViewModel
import com.example.tribial.play.feature.PlayFeature.State

internal object StateToViewModel : (State) -> ViewModel {

    override fun invoke(state: State): ViewModel =
            with(state) {
                ViewModel(
                    username = username,
                    score = score,
                    questionNum = questionIndex+1,
                )
            }
}
