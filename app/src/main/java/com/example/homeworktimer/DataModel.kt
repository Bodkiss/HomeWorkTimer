package com.example.worktimer

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homeworktimer.MyDatabase


class DataModel public constructor(application: Application) : AndroidViewModel(application) {

    private val db: MyDatabase = MyDatabase.getInstance(application)
    val jobModelListLiveData = db.userDao().getAlphabetizedTodoListLiveData()
}