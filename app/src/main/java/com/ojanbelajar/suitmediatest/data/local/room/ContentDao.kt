package com.ojanbelajar.suitmediatest.data.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ojanbelajar.suitmediatest.data.local.entity.GuestEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ContentDao {

    @Query("Select * from guests")
    fun getGuest(): Flow<List<GuestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE,entity = GuestEntity::class)
    fun insertGuest(guestEntity: List<GuestEntity>?)
}