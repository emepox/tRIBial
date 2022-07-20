package com.example.tribial.question.feature

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.NewsPublisher
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.example.tribial.play.datasource.QuestionRepository
import com.example.tribial.play.datasource.models.Question
import com.example.tribial.play.feature.PlayFeature
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.just

class QuestionFeature(
    questionId: Int,
    repository: QuestionRepository
    ): ActorReducerFeature<QuestionFeature.Action, QuestionFeature.Effect, QuestionFeature.State, Nothing>(
    initialState = State(
        dataState = State.DataState.LOADING,
        question = "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        ),
    bootstrapper = BootStrapperImpl(),
    actor = ActorImpl(repository, questionId),
    reducer = ReducerImpl(),
    //newsPublisher = NewsPublisherImpl()
) {

    data class State(
        val dataState: DataState,
        val question: String,
        val answer1: String,
        val answer2: String,
        val answer3: String,
        val answer4: String
    ) {
        enum class DataState {
            LOADING, READY
        }
    }

    sealed class Wish

    sealed class Effect {
        data class QuestionRequested(val question: Question): Effect()
    }

    sealed class News

    sealed class Action {
        object QuestionNeeded: Action()
    }

    class BootStrapperImpl : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> = Observable.fromArray(
            Action.QuestionNeeded
        )

    }

    class ActorImpl(
        private val questionRepository: QuestionRepository,
        private val questionId: Int
        ) : Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<Effect> =
            when(action) {
                is Action.QuestionNeeded -> {
                    questionRepository.getQuestionForId(questionId).map {
                        Effect.QuestionRequested(it)
                    }
                }
            }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            when(effect) {
                is Effect.QuestionRequested ->
                    state.copy(
                        question = effect.question.question,
                        answer1 = effect.question.correctAnswer,
                        answer2 = effect.question.wrongAnswer[0],
                        answer3 = effect.question.wrongAnswer[1],
                        answer4 = effect.question.wrongAnswer[2]
                    )
            }
    }

    class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {
        override fun invoke(wish: Wish, effect: Effect, state: State): News? =
            null
    }

/*
    val currentQuestion = repository.getQuestionForId(id)

    val setCurrentQuestion = currentQuestion.map { question ->
        question.correctAnswer
    }

 */
}
