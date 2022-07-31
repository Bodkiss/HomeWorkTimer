package com.example.homeworktimer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.worktimer.JobModel

class EmployeeViewModel public constructor(application: Application) : AndroidViewModel(application) {

    private val db: MyDatabase = MyDatabase.getInstance(application)
    val allJobs: List<JobModel> = db.userDao().getAlphabetizedTodoList()

    fun insert(jobModel: JobModel) {
        db.userDao().insert(jobModel)
    }
}