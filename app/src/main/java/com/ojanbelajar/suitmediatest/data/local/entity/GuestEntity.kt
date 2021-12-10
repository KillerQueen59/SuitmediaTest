package com.ojanbelajar.suitmediatest.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "guests")
@Parcelize
data class GuestEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="id")
    val id : Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",


    @ColumnInfo(name = "birthdate")
    var birthdate: String = "",
): Parcelable
