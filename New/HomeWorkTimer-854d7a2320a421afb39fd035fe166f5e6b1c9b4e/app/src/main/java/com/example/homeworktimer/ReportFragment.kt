package com.example.worktimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktimer.TaskViewModel
import com.example.homeworktimer.databinding.FragmentReportBinding


class ReportFragment : Fragment() {

    private lateinit var binding: FragmentReportBinding

    private lateinit var adapter: RecViewAdapter

    private val reportModel: ReportViewModel by activityViewModels()
    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecView()


        binding.buttonClearAll.setOnClickListener {
        taskViewModel.deleteAll()
        }
    }

    private fun initRecView() = with(binding) {
        rcWiew.layoutManager = LinearLayoutManager(activity)
        adapter = RecViewAdapter()
        rcWiew.adapter = adapter
        reportModel.jobModelListLiveData.observe(activity as LifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {

        fun newInstance() = ReportFragment()
    }

}
