package com.bangkit.bahanbaku.data.repository

import androidx.lifecycle.LiveData
import com.bangkit.bahanbaku.data.local.room.IngredientDatabase
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService

class IngredientRepository private constructor(
    private val apiService: ApiService,
    database: IngredientDatabase
) {
//    fun getIngredient(search: String)
}