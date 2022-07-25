package com.example.worktimer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.homeworktimer.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator


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
