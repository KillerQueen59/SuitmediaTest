package com.ojanbelajar.suitmediatest.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val image: Int,
    val name: String,
    val date: String,
    val lat: Double,
    val long: Double
): Parcelable
