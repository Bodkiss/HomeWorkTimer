package com.example.homeworktimer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.worktimer.JobModel

@Database(entities = [JobModel::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): MyDao

    companion object{
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MyDatabase::class.java,
                    "my_database")
                    .allowMainThreadQueries().build()
            }

            return INSTANCE as MyDatabase
        }
    }


}