package com.example.homeworktimer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worktimer.JobModel

@Dao
interface MyDao {
    @Query("SELECT * from my_database ORDER BY courentDate ASC")
    fun getAlphabetizedTodoList(): List<JobModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(jobModel: JobModel)

    @Query("DELETE FROM my_database")
    fun deleteAll()

    @Query("DELETE FROM my_database WHERE id = :userId")
    fun deleteByUserId(userId: Int)
}