package com.example.tribial.database

import androidx.room.*

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(questionData: QuestionDataEntity)

    @Query("SELECT * FROM QuestionDataEntity WHERE id LIKE (:id)")
    fun getQuestionById(id: String): QuestionDataEntity

    @Query("SELECT correctAnswer FROM QuestionDataEntity WHERE id LIKE (:id)")
    fun getCorrectAnswer(id: String): String

    @Delete
    fun deleteQuestion(questionData: QuestionDataEntity)
}