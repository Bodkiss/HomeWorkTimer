package com.example.worktimer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktimer.R
import com.example.homeworktimer.databinding.ItemListBinding


class RecViewAdapter: ListAdapter<JobModel,
        RecViewAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemListBinding.bind(view)

        fun bind(item: JobModel) = with(binding){
            tvDate.text = item.courentDate
            tvHours.text = item.hours
            tvJob.text = item.jobDiscription

        }
    }

    class Comparator: DiffUtil.ItemCallback<JobModel>(){
        override fun areItemsTheSame(oldItem: JobModel, newItem: JobModel): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: JobModel, newItem: JobModel): Boolean {
            return  oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return  Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}