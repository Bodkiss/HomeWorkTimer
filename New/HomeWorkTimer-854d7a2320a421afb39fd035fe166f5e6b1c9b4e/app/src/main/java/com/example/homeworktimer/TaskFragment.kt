package com.example.worktimer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.homeworktimer.TaskViewModel
import com.example.homeworktimer.databinding.FragmentTaskBinding

import java.text.SimpleDateFormat
import java.util.*

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    lateinit var dataHelper: DataHelper

    private val timer = Timer()


    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataHelper = DataHelper(requireContext())


        binding.startButton.setOnClickListener { startStopAction() }
        binding.resetButton.setOnClickListener { resetAction() }
        binding.sendButton.setOnClickListener { sendToReport() }
        if (dataHelper.timerCounting()) {
            startTimer()
        } else {
            stopTimer()
            if (dataHelper.startTime() != null && dataHelper.stopTime() != null) {
                val time = Date().time - calcRestartTime().time
                binding.timeTV.text = timeStringFromLong(time)
            }
        }

        timer.scheduleAtFixedRate(TimeTask(), 0, 500)

    }

    private fun sendToReport() {
        val courentDate = SimpleDateFormat("dd/MM/yyyy").format(Date()).toString()
        val hours = binding.timeTV.text.toString()
        val job = binding.etDiscription.text.toString()
        val jobModel = JobModel(courentDate =  courentDate, hours = hours, jobDiscription = job )
        taskViewModel.insert(jobModel)
    }


    companion object {

        fun newInstance() = TaskFragment()
    }

    private inner class TimeTask : TimerTask() {
        override fun run() {
            if (dataHelper.timerCounting()) {
                val time = Date().time - dataHelper.startTime()!!.time
                binding.timeTV.text = timeStringFromLong(time)
            }
        }
    }

    private fun resetAction() {
        dataHelper.setStopTime(null)
        dataHelper.setStartTime(null)
        stopTimer()
        binding.timeTV.text = timeStringFromLong(0)
        binding.etDiscription.text = null
    }

    private fun stopTimer() {
        dataHelper.setTimerCounting(false)
        binding.startButton.text = "Старт"
    }

    private fun startTimer() {
        dataHelper.setTimerCounting(true)
        binding.startButton.text = "Стоп"
    }

    private fun startStopAction() {
        if (dataHelper.timerCounting()) {
            dataHelper.setStopTime(Date())
            stopTimer()
        } else {
            if (dataHelper.stopTime() != null) {
                dataHelper.setStartTime(calcRestartTime())
                dataHelper.setStopTime(null)
            } else {
                dataHelper.setStartTime(Date())
            }
            startTimer()
        }
    }

    private fun calcRestartTime(): Date {
        val diff = dataHelper.startTime()!!.time - dataHelper.stopTime()!!.time
        return Date(System.currentTimeMillis() + diff)
    }

    private fun timeStringFromLong(ms: Long): String {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / (1000 * 60) % 60)
        val hours = (ms / (1000 * 60 * 60) % 24)
        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hours: Long, minutes: Long, seconds: Long): String {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}