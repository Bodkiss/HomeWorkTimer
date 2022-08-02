package com.example.worktimer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.example.homeworktimer.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat


class MainFragment : Fragment() {


    private val fList = listOf(
        TaskFragment.newInstance(),
        ReportFragment.newInstance()
    )

    private val tList = listOf(
        "Нове завдання",
        "Звіт"
    )

    private lateinit var binding: FragmentMainBinding
    private val reportModel: ReportViewModel by activityViewModels()

    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()


        binding.btnCalculate.setOnClickListener {

        var  jobs = reportModel.hoursModelListLiveData



         //   binding.tvTotalTime.text = (a+b).toString()

            var hours = listOf<String>()
            jobs.forEach(){
                hours+=it.hours
            }
           /* for (i in 0..jobs!!.size-1){
                hours += jobs[i].hours
            }*/
            var hoursDouble = listOf<Double>()
            for (i in 0..hours.size-1){
                hoursDouble += hours[i].substringAfter(":").take(2).toDouble()+hours[i].take(2).toDouble()*60
            }
            val endHour = ((hoursDouble.sum())/60)
            binding.tvTotalTime.text = endHour.toString().take(4) + " год"
            if(binding.etMoneyFromHour.text.isNotEmpty()){
                val moneyFromHour = binding.etMoneyFromHour.text.toString()
                val endMoney = moneyFromHour.toDouble()*endHour
                binding.tvEndMoney.text =endMoney.toString().take(4) + " грн."
            }else{
                Toast.makeText(context,"Вкажіть грн/год!", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun init() = with(binding) {
        val adapter = MyAdapter(activity as FragmentActivity, fList)
        vp2.adapter = adapter
        TabLayoutMediator(tabLayout, vp2){
                tab,pos -> tab.text = tList[pos]
        }.attach()
    }

    companion object {

        fun newInstance() = MainFragment()
            }
    }
