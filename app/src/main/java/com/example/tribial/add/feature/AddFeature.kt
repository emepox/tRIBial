package com.example.tribial.add.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.example.tribial.add.feature.AddFeature.State
import com.example.tribial.add.feature.AddFeature.News
import com.example.tribial.add.feature.AddFeature.Effect
import io.reactivex.Observable
import io.reactivex.Observable.empty

internal class AddFeature : ActorReducerFeature<AddFeature.Wish, Effect, State, News>(
    initialState = State(),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val yourData: Any? = null
    )

    sealed class Wish {
        data class AddQuestion(val questionInfo: Question) : Wish()
    }

    sealed class Effect {
        object AddedQuestion: Effect()
    }

    sealed class News {
        object CloseScreen: News()
    }

    class BootStrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> =
            empty()
    }

    class ActorImpl : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> =
            empty()
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            state
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? =
            null
    }

    data class Question(
        val question: String,
        val answer1: Answer,
        val answer2: Answer,
        val answer3: Answer,
        val answer4: Answer
    )

    data class Answer(
        val answer: String,
        val isCorrect: Boolean
    )

}
