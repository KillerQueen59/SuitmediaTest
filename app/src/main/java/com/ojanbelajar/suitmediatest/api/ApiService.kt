package com.ojanbelajar.suitmediatest.api

import com.ojanbelajar.suitmediatest.data.remote.ListGuestResponse
import retrofit2.http.GET

interface ApiService {

    @GET("v2/596dec7f0f000023032b8017")
    suspend fun getGuest(): List<ListGuestResponse>
}