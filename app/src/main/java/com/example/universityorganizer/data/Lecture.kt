package com.example.universityorganizer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lectures_table")
data class Lecture(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val subject: String,
    val place: String,
    val beginTime: Int,
    val endTime: Int,
    val day: String
)
