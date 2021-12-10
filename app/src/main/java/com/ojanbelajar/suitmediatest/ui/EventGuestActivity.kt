package com.ojanbelajar.suitmediatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ojanbelajar.suitmediatest.databinding.ActivityEventGuestBinding
import com.ojanbelajar.suitmediatest.utils.SessionManagement
import org.jetbrains.anko.startActivity

class EventGuestActivity: AppCompatActivity() {

    lateinit var binding: ActivityEventGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val session = SessionManagement(this)

        val name = session.name
        val event = session.event
        val guest = session.guest
        if(!event.isNullOrEmpty()){
            binding.btnEvent.text = event
        }
        if(!guest.isNullOrEmpty()){
            binding.btnGuest.text = guest
        }
        binding.tvName.text = name

        binding.btnEvent.setOnClickListener {
            startActivity<EventListActivity>()
        }
        binding.btnGuest.setOnClickListener {
            startActivity<GuestListActivity>()
        }
    }


}