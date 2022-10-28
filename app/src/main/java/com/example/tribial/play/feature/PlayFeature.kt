package com.example.tribial.play.feature

import com.badoo.mvicore.element.*
import com.badoo.mvicore.feature.BaseFeature
import com.example.tribial.play.datasource.QuestionRepository
import com.example.tribial.play.datasource.models.Question
import com.example.tribial.play.feature.PlayFeature.Effect
import com.example.tribial.play.feature.PlayFeature.News
import com.example.tribial.play.feature.PlayFeature.State
import com.example.tribial.play.feature.PlayFeature.Action
import io.reactivex.Observable

class PlayFeature(
    username: String,
    repository: QuestionRepository,
    ) : BaseFeature<PlayFeature.Wish, Action, Effect, State, News>(
    initialState = State(
        gameState = State.GameState.PLAYING,
        username = username,
        score = 0,
        questionIndex = 0,
        questionList = listOf(),
        answeredCorrectly = State.Dialog.NO_SHOW
    ),
    bootstrapper = BootStrapperImpl(),
    wishToAction = Action::ExecuteWish,
    actor = ActorImpl(repository),
    reducer = ReducerImpl(),
    postProcessor = ReducerImpl.PostProcessorImpl(),
    newsPublisher = ReducerImpl.NewsPublisherImpl(),
) {

    data class State(
        val gameState: GameState,
        val username: String,
        val score: Int,
        val questionIndex: Int,
        val questionList: List<Question>,
        val answeredCorrectly: Dialog

    ) {
        enum class GameState {
            LOADING, PLAYING, END
        }

        enum class Dialog {
            NO_SHOW, SHOW_CORRECT, SHOW_WRONG, SHOW_END
        }
    }

    sealed class Wish {
        data class CheckAnswerProvided(val answer: String) : Wish()
    }

    sealed class Effect {
        data class QuestionsReceived(val questions: List<Question>) : Effect()
        //object AnswerCorrect : Effect()
        data class AnswerCorrect(val grantedPoints: Int): Effect()
        //object AnswerWrong : Effect()
        data class AnswerWrong(val reducedPoints: Int): Effect()
        object NoMoreQuestions: Effect()
        data class NextQuestionReceived(val nextQuestionIndex: Int) : Effect()
    }

    sealed class News {
        data class PresentNextQuestion(val questionId: Int) : News()
        object GameFinished: News()
    }

    sealed class Action {
        object RequestQuestions : Action()
        data class ExecuteWish(val wish: Wish) : Action()
        object NextQuestion : Action()

    }

    // *_*_*_*_*_*_*_*_*_*_*_* BOOTSTRAPPER *_*_*_*_*_*_*_*_*_*_*_*

    class BootStrapperImpl : Bootstrapper<Action> {
        override fun invoke(): Observable<Action> = Observable.fromArray(
            Action.RequestQuestions
        )
    }

    // *_*_*_*_*_*_*_*_*_*_*_* ACTOR *_*_*_*_*_*_*_*_*_*_*_*

    class ActorImpl(private val questionRepository: QuestionRepository) :
        Actor<State, Action, Effect> {
        override fun invoke(state: State, action: Action): Observable<Effect> =
            when (action) {
                is Action.RequestQuestions -> {
                    questionRepository.getQuestions().map {
                        Effect.QuestionsReceived(it)
                    }
                }
                is Action.ExecuteWish -> when (action.wish) {
                    is Wish.CheckAnswerProvided -> {
                        if (action.wish.answer == state.questionList[state.questionIndex].correctAnswer) {
                            Observable.just(Effect.AnswerCorrect(GRANTED_POINTS))
                        } else {
                            Observable.just(Effect.AnswerWrong(PENALTY_POINTS))
                        }
                    }
                }
                is Action.NextQuestion -> {
                    val nextQuestionIndex = state.questionIndex + 1
                    val listSize = state.questionList.size
                    if (nextQuestionIndex < listSize) {
                        Observable.just(Effect.NextQuestionReceived(nextQuestionIndex))
                    }
                    else {
                        Observable.just(Effect.NoMoreQuestions)
                    }
                }
            }

         companion object {
            private const val GRANTED_POINTS = 100
            private const val PENALTY_POINTS = 10
        }
    }

    // *_*_*_*_*_*_*_*_*_*_*_* REDUCER *_*_*_*_*_*_*_*_*_*_*_*
    // NEEDS TO BE DUMB
    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State =
            when (effect) {
                is Effect.QuestionsReceived ->
                    state.copy(
                        questionList = effect.questions
                    )
                is Effect.AnswerCorrect ->
                    state.copy(
                        score = state.score + effect.grantedPoints,
                    )
                is Effect.AnswerWrong ->
                    state.copy(
                        score = state.score - effect.reducedPoints
                    )
                is Effect.NextQuestionReceived -> state.copy(
                    questionIndex = effect.nextQuestionIndex
                )
                is Effect.NoMoreQuestions ->
                    state.copy(
                        gameState = State.GameState.END
                    )
            }

        // *_*_*_*_*_*_*_*_*_*_*_* NEWS PUBLISHER *_*_*_*_*_*_*_*_*_*_*_*

        class NewsPublisherImpl : NewsPublisher<Action, Effect, State, News> {
            override fun invoke(action: Action, effect: Effect, state: State): News? =
                when (effect) {
                    is Effect.QuestionsReceived -> {
                        state.questionList.firstOrNull()?.let { firstQuestion ->
                            News.PresentNextQuestion(firstQuestion.questionId)
                        }
                    }
                    is Effect.NextQuestionReceived -> {
                        state.questionList.let { question ->
                            if (state.questionIndex < (state.questionList.size)) {
                                News.PresentNextQuestion(question[state.questionIndex].questionId)

                            } else {
                                null
                            }
                        }
                    }
                    is Effect.NoMoreQuestions -> News.GameFinished
                    else -> {
                        null
                    }
                }
        }

        // *_*_*_*_*_*_*_*_*_*_*_* POST PROCESSOR *_*_*_*_*_*_*_*_*_*_*_*

        class PostProcessorImpl : PostProcessor<Action, Effect, State> {
            override fun invoke(action: Action, effect: Effect, state: State): Action? =
                when (effect) {
                    is Effect.AnswerCorrect, is Effect.AnswerWrong -> Action.NextQuestion
                    else -> {null}
                }
        }
    }
}
