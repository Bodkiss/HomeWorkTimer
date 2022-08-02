package com.example.homeworktimer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worktimer.JobModel

@Dao
interface MyDao {
    @Query("SELECT * from my_database ORDER BY courentDate ASC")
    fun getAlphabetizedTodoList(): List<JobModel>

    @Query("SELECT * from my_database ORDER BY courentDate ASC")
    fun getAlphabetizedTodoListLiveData(): LiveData<List<JobModel>>

    /*@Query("SELECT  DISTINCT hours  from my_database ")
    fun getOnlyHoursListLiveData(): LiveData<List<String>>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(jobModel: JobModel)

    @Query("DELETE FROM my_database")
    suspend fun deleteAll()

    @Query("DELETE FROM my_database WHERE id = :userId")
    fun deleteByUserId(userId: Int)
}