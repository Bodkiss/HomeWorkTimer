package com.example.homeworktimer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worktimer.JobModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel (application: Application) : AndroidViewModel(application) {

    private val db: MyDatabase = MyDatabase.getInstance(application)
    //val allJobs: List<JobModel> = db.userDao().getAlphabetizedTodoList()

    fun insert(jobModel: JobModel) {
        viewModelScope.launch {

            withContext(Dispatchers.IO){

                db.userDao().insert(jobModel)

                //mutableLiveData.post(data)
            }

        }
    }
    fun deleteAll(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.userDao().deleteAll()
            }
        }
    }


}