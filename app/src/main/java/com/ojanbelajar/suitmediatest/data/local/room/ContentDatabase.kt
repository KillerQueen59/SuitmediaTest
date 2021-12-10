package com.ojanbelajar.suitmediatest.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ojanbelajar.suitmediatest.data.local.entity.GuestEntity

@Database(entities = [GuestEntity::class], version = 1, exportSchema = false)
abstract class ContentDatabase: RoomDatabase() {
    companion object {

        @Volatile
        private var INSTANCE: ContentDatabase? = null

        fun getInstance(context: Context): ContentDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ContentDatabase::class.java,
                    "content.db"
                ).fallbackToDestructiveMigration()
                    .build().apply {
                        INSTANCE = this
                    }
            }
    }    abstract fun contentDao(): ContentDao



}