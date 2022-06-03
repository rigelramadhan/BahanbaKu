package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.bahanbaku.data.remote.response.IngredientResults
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import com.bangkit.bahanbaku.utils.Result
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getIngredient(token: String, search: String): LiveData<Result<IngredientResults>> = liveData {
        emit(Result.Loading)
        try {
            val ingredients = apiService.getIngredient(token, search).results
            emit(Result.Success(ingredients))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}