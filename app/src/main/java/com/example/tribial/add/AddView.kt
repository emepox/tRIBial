package com.example.tribial.add

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.R
import com.example.tribial.add.AddView.Event
import com.example.tribial.add.AddView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface AddView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    fun interface Factory : ViewFactoryBuilder<Nothing?, AddView>
}


class AddViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    AddView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_add
    ) : AddView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<AddView> =
            ViewFactory {
                AddViewImpl(
                    it.inflate(layoutRes)
                )
            }
    }

    override fun accept(vm: AddView.ViewModel) {
    }
}
