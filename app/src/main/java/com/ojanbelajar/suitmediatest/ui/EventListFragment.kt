package com.ojanbelajar.suitmediatest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.suitmediatest.adapter.EventAdapter
import com.ojanbelajar.suitmediatest.data.remote.Event
import com.ojanbelajar.suitmediatest.databinding.FragmentEventListBinding

class EventListFragment: Fragment() {

    private lateinit var binding : FragmentEventListBinding
    lateinit var adapter: EventAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EventAdapter(requireActivity())
        adapter.setData(setDummyData())
        setupRV()
    }

    fun setDummyData(): ArrayList<Event>{
        val listEvent = ArrayList<Event>()
        listEvent.add(Event(0,"Iconfest","28-01-2021",40.712776,-74.005974))
        listEvent.add(Event(0,"ITToday","15-03-2020",51.507351,-0.127758))
        listEvent.add(Event(0,"Paradigma","07-11-2021",-33.924870,18.424055))
        listEvent.add(Event(0,"Vision","03-05-2019",25.043551135505613, 46.56966750407985))
        listEvent.add(Event(0,"SynchroFEST","02-01-2018",36.43960404550113, 128.36466720896559))
        listEvent.add(Event(0,"EduFest","17-04-2021",-7.183385234975524, 109.38568029078947))
        listEvent.add(Event(0,"Youtube Rewind","21-08-2021",-34.73770627205564, 144.36614771527533))
        return listEvent
    }

    fun setupRV(){
        binding.rvEvent.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvEvent.adapter = adapter

    }
}