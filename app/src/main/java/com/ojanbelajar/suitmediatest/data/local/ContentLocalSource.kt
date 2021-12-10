package com.ojanbelajar.suitmediatest.data.local

import com.ojanbelajar.suitmediatest.data.local.entity.GuestEntity
import kotlinx.coroutines.flow.Flow

interface ContentLocalSource {
    fun getGuest(): Flow<List<GuestEntity>>

    fun insertGuest(guest: List<GuestEntity>)
}
