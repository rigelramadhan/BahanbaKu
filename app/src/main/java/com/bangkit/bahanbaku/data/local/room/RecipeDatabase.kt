package com.bangkit.bahanbaku.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bangkit.bahanbaku.data.remote.response.FoodEntity
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var instance: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, RecipeDatabase::class.java, "recipes.db")
                .build()
        }.also { instance = it }
    }
}