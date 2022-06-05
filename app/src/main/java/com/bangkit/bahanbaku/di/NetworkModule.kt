package com.bangkit.bahanbaku.di

import com.bangkit.bahanbaku.data.remote.retrofit.ApiConfig
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.data.remote.retrofit.ApiServiceML
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiConfig.getApiService()
    }

    @Provides
    @Singleton
    fun provideApiServiceML(): ApiServiceML {
        return ApiConfig.getApiServiceML()
    }
}