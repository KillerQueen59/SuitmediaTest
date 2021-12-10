package com.ojanbelajar.suitmediatest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ojanbelajar.suitmediatest.adapter.GuestAdapter
import com.ojanbelajar.suitmediatest.data.Resource
import com.ojanbelajar.suitmediatest.data.remote.Guest
import com.ojanbelajar.suitmediatest.databinding.ActivityGuestListBinding
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast


@AndroidEntryPoint
class GuestListActivity: AppCompatActivity() {
    lateinit var binding: ActivityGuestListBinding
    lateinit var model : GuestListViewModel
    lateinit var adapter: GuestAdapter
    private var data = ArrayList<Guest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model =  ViewModelProvider(this).get(GuestListViewModel::class.java)
        adapter = GuestAdapter(this)
        getGuest()
        setRv()
    }

    fun getGuest(){
        model.getGuest().observe(this,{ result ->
            if (result != null){
                when(result){
                    is Resource.Loading ->{
                        binding.progress.isVisible = true
                        binding.rvGuest.isVisible = false
                    }
                    is Resource.Success -> {
                        for(i in result.data!!){
                            val guest = Guest(i.id,i.name,i.birthdate)
                            data.add(guest)
                        }
                        binding.progress.isVisible = false
                        binding.rvGuest.isVisible = true
                        adapter.setData(data)
                    }
                    is Resource.Error -> {
                        binding.progress.isVisible = false
                        binding.rvGuest.isVisible = true
                        toast(result.message.toString())

                    }
                }
            }
        })
    }

    fun setRv(){
        binding.rvGuest.layoutManager = GridLayoutManager(this,2)
        binding.rvGuest.adapter = adapter
    }


}