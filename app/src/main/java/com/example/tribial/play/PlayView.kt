package com.example.tribial.play

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.badoo.ribs.core.Node
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactoryBuilder
import com.badoo.ribs.core.customisation.inflate
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.tribial.R
import com.example.tribial.api.ApiDataSource
import com.example.tribial.database.QuestionDao
import com.example.tribial.play.PlayView.Event
import com.example.tribial.play.PlayView.ViewModel
import com.example.tribial.database.QuestionDataEntity
import com.example.tribial.play.datasource.models.Question
import com.example.tribial.play.feature.PlayFeature
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer
import kotlinx.coroutines.flow.Flow

interface PlayView : RibView,
    ObservableSource<Event>,
    Consumer<ViewModel> {

    sealed class Event {
        //object ViewInflated: Event()
    }

    data class ViewModel(
        val username: String = "",
        val score: Int = 0,
        val questionNum: Int = 0,

        )

    fun interface Factory : ViewFactoryBuilder<Nothing?, PlayView>

    interface ViewDependency {
        val remoteDataSource: ApiDataSource
        val localDataSource: QuestionDao
    }
}


class PlayViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<Event> = PublishRelay.create(),
    //private val remoteDataSource: ApiDataSource,
    //private val localDataSource: RoomDataSource
) : AndroidRibView(),
    PlayView,
    ObservableSource<Event> by events,
    Consumer<ViewModel> {

    private val questionBlock: FrameLayout = findViewById(R.id.fl_questionBlock)

    override fun getParentViewForSubtree(subtreeOf: Node<*>): ViewGroup = questionBlock


    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.rib_play
    ) : ViewFactoryBuilder<Nothing?, PlayView> {

        override fun invoke(viewDependency: Nothing?): ViewFactory<PlayView> =
            ViewFactory {
                PlayViewImpl(
                    androidView = it.inflate(layoutRes),
                   // remoteDataSource = viewDependency.remoteDataSource,
                   // localDataSource = viewDependency.localDataSource
                )
            }
    }

    private val username: TextView = findViewById(R.id.tv_play_username)
    private val score: TextView = findViewById(R.id.tv_play_score)
    private val questionNum: TextView = findViewById(R.id.tv_play_questionNum)

    override fun accept(vm: PlayView.ViewModel) {
        username.text = vm.username // Username
        score.text = "${vm.score}" // Score
        questionNum.text = "${vm.questionNum} / 10" // Number of question
    }


    /*
        private val modalController = ModalController(context)

        modalController.bind(
        ModalControllerModel.Show(

        )
    )
     */

}
