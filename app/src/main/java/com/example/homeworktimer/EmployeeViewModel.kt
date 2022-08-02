package com.example.homeworktimer

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.worktimer.JobModel

class EmployeeViewModel (var application: Application): ViewModel()
{
    private val db:MyDatabase = MyDatabase.getInstance(application)
    val allJobs : List<JobModel> = db.userDao().getAlphabetizedTodoList()

    fun insert(jobModel: JobModel){
        db.userDao().insert(jobModel)
    }


}