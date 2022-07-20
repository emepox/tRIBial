package com.example.tribial.play

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.childaware.childAware
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.example.tribial.play.feature.PlayFeature
import com.example.tribial.play.mapper.PlayNewsToOutput
import com.example.tribial.play.mapper.QuestionOutputToPlayWish
import com.example.tribial.play.mapper.StateToViewModel
import com.example.tribial.play.routing.PlayRouter
import com.example.tribial.question.Question
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

internal class PlayInteractor(
    buildParams: BuildParams<*>,
    private val feature: PlayFeature,
    private val backStack: BackStack<PlayRouter.Configuration>
) : Interactor<Play, PlayView>(
    buildParams = buildParams
) {

    /*
    private val newsConsumer: Consumer<PlayFeature.News> = Consumer { news ->
        when(news) {
            is PlayFeature.News.GameFinished -> {
                Log.e("Mystery", "PlayInteractor: NewsConsumer - PresentQuestion ${news.questionId}")
                backStack.replace(AppRootRouter.Configuration.Ranking)
            }
        }
    }

     */

    private val stateConsumer: Consumer<Int> = Consumer {
        backStack.replace(PlayRouter.Configuration.Question(it))
    }

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
            //bind(feature.news to newsConsumer)
            bind(feature.wrapToObservable().map {
                    it.questionList[it.questionIndex].questionId
                }
                .distinctUntilChanged() to stateConsumer
            )
            bind(feature.news to rib.output using PlayNewsToOutput)
        }
        childAware(nodeLifecycle) {
            whenChildAttached<Question> { lifecycle, child ->
                lifecycle.createDestroy {
                    bind(child.output to feature using QuestionOutputToPlayWish)
                }
            }
        }
    }

    override fun onViewCreated(view: PlayView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(feature to view using StateToViewModel)
           // bind(view to feature using ViewEventToWish)
          //  bind(view to PlayAnalytics using ViewEventToAnalyticsEvent)
        }
    }

    private fun <T> ObservableSource<out T>.wrapToObservable(): Observable<T> =
        Observable.wrap(cast())

    private inline fun <reified T> Any?.cast(): T = this as T


}
