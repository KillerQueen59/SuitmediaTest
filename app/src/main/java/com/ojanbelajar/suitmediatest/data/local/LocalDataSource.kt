package com.ojanbelajar.suitmediatest.data.local

import com.ojanbelajar.suitmediatest.data.local.entity.GuestEntity
import com.ojanbelajar.suitmediatest.data.local.room.ContentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: ContentDao
): ContentLocalSource{
    override fun getGuest(): Flow<List<GuestEntity>> = dao.getGuest()

    override fun insertGuest(guest: List<GuestEntity>) = dao.insertGuest(guest)

}
