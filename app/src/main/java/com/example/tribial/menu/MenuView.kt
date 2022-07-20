package com.example.tribial.menu

import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.R
import com.example.tribial.app_root.AppRootView
import com.example.tribial.menu.MenuView.Event
import com.example.tribial.menu.MenuView.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface MenuView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event {
        data class PlayButtonClicked(val username: String) : Event()
        object AddButtonClicked : Event()
        object RankingButtonClicked : Event()
    }
    data class ViewModel(
        val i: Int = 0
    )

    fun interface Factory : ViewFactoryBuilder<Nothing?, MenuView>
}


class MenuViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    MenuView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    private val playButton: Button by lazy {findViewById(R.id.btn_play)}
    private val editText: EditText by lazy { findViewById(R.id.et_username) }
    private val addButton: Button by lazy {findViewById(R.id.btn_add)}
    private val rankingButton: Button by lazy {findViewById(R.id.btn_ranking)}

    init {
        setListeners()
    }

    private fun setListeners() {
        playButton.setOnClickListener {
            val usernameInput = editText.text.toString()
            if (usernameInput.isNotBlank()) {
                events.accept(Event.PlayButtonClicked(usernameInput))
            }
        }
        addButton.setOnClickListener { events.accept(Event.AddButtonClicked) }
        rankingButton.setOnClickListener { events.accept(Event.RankingButtonClicked)}
    }

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_menu
    ) : MenuView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<MenuView> =
            ViewFactory {
                MenuViewImpl(
                    it.inflate(layoutRes)
                )
            }
    }

    override fun accept(vm: MenuView.ViewModel) {
    }
}
