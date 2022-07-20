package com.example.tribial.ranking

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.R
import com.example.tribial.ranking.RankingView.Event
import com.example.tribial.ranking.RankingView.ViewModel
import com.example.tribial.ranking.utils.RankingRecyclerAdapter
import com.example.tribial.ranking.utils.RecyclerData
import com.example.tribial.ranking.utils.RecyclerRepository
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface RankingView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event

    data class ViewModel(
        val i: Int = 0
    )

    fun interface Factory : ViewFactoryBuilder<Nothing?, RankingView>
}


class RankingViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create()
) : AndroidRibView(),
    RankingView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_ranking
    ) : RankingView.Factory {
        override fun invoke(deps: Nothing?): ViewFactory<RankingView> =
            ViewFactory {
                RankingViewImpl(
                    it.inflate(layoutRes)
                )
            }
    }

    override fun accept(vm: RankingView.ViewModel) {
    }

    init {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_ranking)
        val myData: List<RecyclerData> = RecyclerRepository().getData()
        val myAdapter = RankingRecyclerAdapter(myData)

        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}

