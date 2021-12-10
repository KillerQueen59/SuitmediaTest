package com.ojanbelajar.suitmediatest.data

import com.ojanbelajar.suitmediatest.data.remote.Guest
import com.ojanbelajar.suitmediatest.data.remote.ListGuestResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getGuest(): Flow<Resource<List<Guest>>>

}