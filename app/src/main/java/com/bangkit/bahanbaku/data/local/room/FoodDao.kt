package com.bangkit.bahanbaku.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.bahanbaku.data.remote.response.FoodEntity

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getFoods(): LiveData<List<FoodEntity>>

    @Query("DELETE FROM food")
    fun deleteAllFoods()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoods(foods: List<FoodEntity>)
}