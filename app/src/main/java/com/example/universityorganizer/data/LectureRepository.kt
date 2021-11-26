package com.example.universityorganizer.data

import android.content.Context
import android.os.AsyncTask


class LectureRepository(context: Context) {

    private var db: LectureDao = LectureDatabase.getDatabase(context).lectureDao()


    //Fetch All the lectures
    fun getAllUsers(): List<Lecture> {
        return db.getAllLectures()
    }

    // Insert new Lecture
    fun insertLecture(lecture: Lecture) {
        insertAsyncTask(db).execute(lecture)
    }

    // update Lecture
    fun updateLecture(lecture: Lecture) {
        db.update(lecture)
    }

    // Delete Lecture
    fun deleteLecture(lecture: Lecture) {
        db.delete(lecture)
    }

    private class insertAsyncTask internal constructor(private val lectureDao: LectureDao) :
        AsyncTask<Lecture, Void, Void>() {

        override fun doInBackground(vararg params: Lecture): Void? {
            lectureDao.insert(params[0])
            return null
        }
    }
}