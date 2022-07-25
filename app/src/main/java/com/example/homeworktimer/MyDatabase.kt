package com.example.homeworktimer

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.worktimer.JobModel

@Database(entities = [JobModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): MyDao
}