package com.example.worktimer

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.homeworktimer.EmployeeViewModel
import com.example.homeworktimer.MyDao
import com.example.homeworktimer.MyDatabase
import com.example.homeworktimer.MyViewModelFactory
import com.example.homeworktimer.databinding.FragmentReportBinding


class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding

    private lateinit var adapter: RecViewAdapter

    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* val db = Room.databaseBuilder(
             requireContext().applicationContext,
             MyDatabase::class.java, "my_database"
         ).allowMainThreadQueries().
         build()
         val userDao = db.userDao()
         userDao.getAlphabetizedTodoList()*/

        initRecView()


        binding.buttonClearAll.setOnClickListener {
            /*userDao.deleteAll()
        userDao.getAlphabetizedTodoList()*/


        }
    }

    private fun initRecView() = with(binding) {

        dataModel.jobModelListLiveData.observe(activity as LifecycleOwner) {
            rcWiew.layoutManager = LinearLayoutManager(activity)
            adapter = RecViewAdapter()
            rcWiew.adapter = adapter
            adapter.submitList(it)
        }
    }

    companion object {

        fun newInstance() = ReportFragment()
    }

}
