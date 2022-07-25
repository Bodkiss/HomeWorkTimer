package com.example.worktimer

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class DataModel: ViewModel() {

    val fromTaskToReport: MutableLiveData<JobModel> by lazy {
        MutableLiveData<JobModel>()
    }




}