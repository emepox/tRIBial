package com.example.tribial.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [QuestionDataEntity::class], version = 1)
@TypeConverters(ListStringConverter::class)
abstract class AppDB: RoomDatabase() {
    abstract fun questionDao(): QuestionDao

/*
    companion object{
        @Volatile
        private var instance:AppDB? = null

        fun getInstance(context: Context):AppDB?{
            if (instance == null){
                synchronized(AppDB::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "database"
                    )
                        .addCallback(StartingDatabase(context))
                        .build()
                }
            }
            return instance
        }
    }

 */

}


