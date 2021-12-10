package com.ojanbelajar.suitmediatest.data.remote

import com.ojanbelajar.suitmediatest.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource@Inject constructor(private val apiService: ApiService): RemoteDataInterface {
    override suspend fun getGuest(): Flow<ApiResponse<List<ListGuestResponse>>> {
        return flow {
            try {
                val response = apiService.getGuest()
                if (!response.equals(null)) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO )
    }

}