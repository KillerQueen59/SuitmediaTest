package com.ojanbelajar.suitmediatest.data

import com.ojanbelajar.suitmediatest.data.local.ContentLocalSource
import com.ojanbelajar.suitmediatest.data.remote.ApiResponse
import com.ojanbelajar.suitmediatest.data.remote.Guest
import com.ojanbelajar.suitmediatest.data.remote.ListGuestResponse
import com.ojanbelajar.suitmediatest.data.remote.RemoteDataInterface
import com.ojanbelajar.suitmediatest.utils.AppExecutors
import com.ojanbelajar.suitmediatest.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataRepository  @Inject constructor(
    private val contentRemoteSource: RemoteDataInterface,
    private val contentLocalSource: ContentLocalSource,
    private val appExecutors: AppExecutors
): Repository {
        override fun getGuest(): Flow<Resource<List<Guest>>> =
            object : NetworkBoundResource<List<Guest>,List<ListGuestResponse>>(){
                override fun loadFromDB(): Flow<List<Guest>> {
                    return contentLocalSource.getGuest().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Guest>?): Boolean  = data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<ListGuestResponse>>> =
                    contentRemoteSource.getGuest()

                override suspend fun saveCallResult(data: List<ListGuestResponse>) {
                    val list = DataMapper.mapResponsesToEntities(data)
                    appExecutors.diskIO().execute{
                        contentLocalSource.insertGuest(list)
                    }
                }
            }.asFlow()
}