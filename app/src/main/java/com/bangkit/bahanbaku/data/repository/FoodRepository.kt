package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.FoodDatabase
import com.bangkit.bahanbaku.data.remote.response.FoodEntity
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result

class FoodRepository private constructor(private val apiService: ApiService, private val database: FoodDatabase) {
    fun getFoods(): LiveData<Result<List<FoodEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFoods()
            val foods = response.list
            emit(Result.Success(foods))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: FoodRepository? = null

        fun getInstance(apiService: ApiService, database: FoodDatabase): FoodRepository =
            instance ?: synchronized(this) {
                instance ?: FoodRepository(apiService, database)
            }.also { instance = it }
    }
}