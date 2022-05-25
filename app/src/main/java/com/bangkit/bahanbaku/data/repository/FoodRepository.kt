package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.local.room.FoodDatabase
import com.bangkit.bahanbaku.data.remote.response.FoodEntity
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: FoodDatabase
) {
//    fun getFoods(): LiveData<Result<List<FoodEntity>>> = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.getFoods()
//            val foods = response.list
//            emit(Result.Success(foods))
//        } catch (e: Exception) {
//            emit(Result.Error(e.message.toString()))
//        }
//    }
}