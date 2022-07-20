package com.example.tribial.play.routing

import com.example.tribial.play.Play
import com.example.tribial.question.Question
import com.example.tribial.question.QuestionBuilder

class PlayChildBuilders(
    dependency: Play.Dependency
) {

    private val subtreeDeps = SubtreeDependency(dependency)

    val question = QuestionBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: Play.Dependency
    ): Play.Dependency by dependency,
            Question.Dependency
}