package com.ojanbelajar.suitmediatest.di

import android.app.Application
import com.ojanbelajar.suitmediatest.api.ApiService
import com.ojanbelajar.suitmediatest.data.DataRepository
import com.ojanbelajar.suitmediatest.data.Repository
import com.ojanbelajar.suitmediatest.data.local.ContentLocalSource
import com.ojanbelajar.suitmediatest.data.local.LocalDataSource
import com.ojanbelajar.suitmediatest.data.local.room.ContentDao
import com.ojanbelajar.suitmediatest.data.local.room.ContentDatabase
import com.ojanbelajar.suitmediatest.data.remote.RemoteDataInterface
import com.ojanbelajar.suitmediatest.data.remote.RemoteDataSource
import com.ojanbelajar.suitmediatest.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideBase() = "http://www.mocky.io/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, BASEURL: String): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASEURL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideContentLocalSource(localSource: LocalDataSource): ContentLocalSource =  localSource


    @Provides
    @Singleton
    fun provideContentRemoteSource(remoteSource: RemoteDataSource): RemoteDataInterface = remoteSource

    @Provides
    @Singleton
    fun provideContentRepository(
        contentRemoteSource: RemoteDataInterface,
        contentLocalSource: ContentLocalSource,
        appExecutors: AppExecutors
    ): Repository = DataRepository(contentRemoteSource,contentLocalSource,appExecutors)

    @Singleton
    @Provides
    fun provideContentDatabase(
        application: Application
    ): ContentDatabase = ContentDatabase.getInstance(application)

    @Singleton
    @Provides
    fun contentDao(db: ContentDatabase): ContentDao = db.contentDao()

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors = AppExecutors()

}