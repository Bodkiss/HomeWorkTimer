package com.example.worktimer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_database")
data class JobModel(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    var courentDate: String,
    var hours:String,
    var jobDiscription:String
        )