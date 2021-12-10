package com.ojanbelajar.suitmediatest.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListGuestResponse(
    val id: Int,
    val name: String,
    val birthdate: String
): Parcelable
