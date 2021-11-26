package com.example.universityorganizer.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface LectureDao {


    @Insert(onConflict = REPLACE)
    fun insert(lecture: Lecture)

    @Update
    fun update(lecture: Lecture)

    @Delete
    fun delete(lecture: Lecture)

    @Query("Select * From lectures_table")
    fun getAllLectures(): List<Lecture>
}