package com.ojanbelajar.suitmediatest.data.remote

import kotlinx.coroutines.flow.Flow

interface RemoteDataInterface {
    suspend fun getGuest(): Flow<ApiResponse<List<ListGuestResponse>>>
}
