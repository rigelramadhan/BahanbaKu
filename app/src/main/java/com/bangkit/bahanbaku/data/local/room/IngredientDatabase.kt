package com.bangkit.bahanbaku.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class IngredientDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao

    companion object {
        @Volatile
        private var instance: IngredientDatabase? = null

        fun getInstance(context: Context): IngredientDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context,
                IngredientDatabase::class.java,
                "ingredient.db"
            ).build()
        }.also { instance = it }
    }
}