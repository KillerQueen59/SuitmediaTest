package com.ojanbelajar.suitmediatest.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ojanbelajar.suitmediatest.data.Repository

class GuestListViewModel @ViewModelInject constructor(
    private val contentRepository: Repository
): ViewModel(){
    fun getGuest() = contentRepository.getGuest().asLiveData()
}