package com.example.universityorganizer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Lecture::class), version = 1,exportSchema = false)
abstract class LectureDatabase: RoomDatabase() {

    abstract fun lectureDao(): LectureDao


    /** the companion object and the code inside it, was made
     * to ensure that only one instance of the database exist
     * because having more than one instance is bad for the performance of the app
     * */

    companion object{
        @Volatile
        private var INSTANCE: LectureDatabase? = null

        fun getDatabase(context: Context):LectureDatabase{
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LectureDatabase::class.java,
                    "lectures_table"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}