package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import com.bangkit.bahanbaku.data.local.room.IngredientDatabase
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: IngredientDatabase
) {
//    fun getIngredient(search: String)
}