package com.bangkit.bahanbaku.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getRecipes(): LiveData<List<RecipeEntity>>

    @Query("DELETE FROM recipe")
    fun deleteAllRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipes: List<RecipeEntity>)
}