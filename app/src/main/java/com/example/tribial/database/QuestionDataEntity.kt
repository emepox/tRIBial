package com.example.tribial.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class QuestionDataEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo (name = "correctAnswer") val correctAnswer: String,
    @ColumnInfo (name = "incorrectAnswers") val incorrectAnswers: List<String>
) : Parcelable
