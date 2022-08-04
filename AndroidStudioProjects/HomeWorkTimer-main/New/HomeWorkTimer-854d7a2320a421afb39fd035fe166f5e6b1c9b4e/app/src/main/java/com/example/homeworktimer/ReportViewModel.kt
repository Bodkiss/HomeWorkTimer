package com.example.worktimer

import android.app.Application
import android.content.Intent
import androidx.lifecycle.*
import com.example.homeworktimer.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ReportViewModel (application: Application) : AndroidViewModel(application) {

    private val db: MyDatabase = MyDatabase.getInstance(application)
    val jobModelListLiveData = db.userDao().getAlphabetizedTodoListLiveData()

   val hoursModelListLiveData = db.userDao().getAlphabetizedTodoList()

    fun getAllList(): LiveData<List<JobModel>> {
      return  db.userDao().getAlphabetizedTodoListLiveData()
    }



}