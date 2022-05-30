package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result
import java.io.File
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val apiService: ApiService,
//    private val database: FoodDatabase
) {
    fun postSnapFood(token: String, file: File): LiveData<Result<List<String>>> = liveData {
        emit(Result.Loading)
        try {
            emit(Result.Success(listOf("Food1", "Food2", "Food3")))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}