package com.example.tribial.app_root

import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.routing.transition.handler.Slider
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.example.tribial.R
import com.example.tribial.app_root.AppRootView.Event
import com.example.tribial.app_root.AppRootView.ViewModel
import com.example.tribial.app_root.routing.AppRootRouter
import com.example.tribial.play.PlayView
import com.example.tribial.play.PlayViewImpl
import com.example.tribial.play.Slider2
import com.example.tribial.play.routing.PlayRouter
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface AppRootView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    fun interface Factory : ViewFactoryBuilder<Nothing?, AppRootView>
}



class AppRootViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    AppRootView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    private val childContainer: FrameLayout by lazy  {findViewById(R.id.childContainer)}


    override fun getParentViewForSubtree(subtreeOf: Node<*>): ViewGroup = childContainer

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_app_root
    ) : AppRootView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<AppRootView> =
            ViewFactory {
                AppRootViewImpl(
                    it.inflate(layoutRes)
                )
            }
    }

    override fun accept(vm: AppRootView.ViewModel) {
    }
}
