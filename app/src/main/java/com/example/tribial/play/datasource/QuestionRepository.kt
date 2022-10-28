package com.example.tribial.play.datasource

import com.example.tribial.play.datasource.models.Question
import io.reactivex.Observable

interface QuestionRepository{

    fun getQuestions(): Observable<List<Question>>

    fun getQuestionForId(questionId: Int): Observable<Question>

}

class DummyRepository: QuestionRepository{

   private val questions: List<Question> = listOf(
        Question(
            100,
            "When was the original Star Wars: Battlefront II released?",
            "October 31, 2005",
            listOf(
                "December 18, 2004",
                "November 21, 2006",
                "September 9, 2007"
            )
        ),
       Question(
           101,
           "Who had a 1973 hit with the song 'Hocus Pocus'?",
           "Focus",
           listOf(
               "Pilot",
               "Rush",
               "AC/DC"
           )
       ),
       Question(
           102,
           "What is the area of a circle with a diameter of 20 inches if &pi;= 3.1415?",
           "314.15 Inches",
           listOf(
               "380.1215 Inches",
               "3141.5 Inches",
               "1256.6 Inches"
           )
       ),
       Question(
           103,
           "Which of these games was the earliest known first-person shooter with a known time of publication?",
           "Spasim",
           listOf(
               "Doom",
               "Wolfenstein",
               "Quake"
           )
       ),
       Question(
           104,
           "In the game 'Cave Story', what is the character Balrog's catchphrase?",
           "Huzzah!",
           listOf(
               "Yes!",
               "Whoa there!",
               "Nyeh heh heh!"
           )
       ),
       Question(
           105,
           "The main antagonist of the second part of JoJo's Bizarre Adventure is which of the following?",
           "Kars",
           listOf(
               "Erina Joestar",
               "Santana",
               "Wired Beck"
           )
       ),
       Question(
           106,
           "Johnny Cash did a cover of this song written by lead singer of Nine Inch Nails, Trent Reznor.",
           "Hurt",
           listOf(
               "Closer",
               "A Warm Place",
               "Big Man with a Gun"
           )
       ),
       Question(
           107,
           "Approximately how many Apple I personal computers were created?",
           "200",
           listOf(
               "100",
               "500",
               "1000"
           )
       ),
       Question(
           108,
           "What studio animated Ouran High School Host Club?",
           "Bones",
           listOf(
               "Production I.G",
               "Kyoto Animation",
               "xebec"
           )
       ),
       Question(
           109,
           "In Black Hammer, what dimension does Colonel Weird travel through?",
           "Para-Zone",
           listOf(
               "Hyperspace",
               "Mirror Universe",
               "Phantom Zone"
           )
       )
    )

    override fun getQuestions(): Observable<List<Question>> = Observable.just(questions)

    override fun getQuestionForId(questionId: Int): Observable<Question> = Observable.just(questions.first{it.questionId == questionId})

}

