package com.ojanbelajar.suitmediatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ojanbelajar.suitmediatest.R
import com.ojanbelajar.suitmediatest.databinding.ActivityEventListBinding

class EventListActivity: AppCompatActivity() {

    lateinit var binding: ActivityEventListBinding
    private val eventListFragment: Fragment = EventListFragment()
    private val eventMapFragment: Fragment = EventMapFragment()

    private var page = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        commitFragment(eventListFragment)
        binding.btnArticle.setOnClickListener {
            clearFragmentStack()
            if(page) commitFragment(eventMapFragment)
            else commitFragment(eventListFragment)
            page = !page
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun commitFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

    private fun clearFragmentStack(){
        val fm = this.supportFragmentManager
        for ( i in 0..fm.backStackEntryCount){
            fm.popBackStack()
        }
    }



}