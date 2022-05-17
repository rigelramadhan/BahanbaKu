package com.bangkit.bahanbaku.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.bahanbaku.data.remote.response.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): FoodDao

    companion object {
        @Volatile
        private var instance: ProfileDatabase? = null

        fun getInstance(context: Context): ProfileDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, ProfileDatabase::class.java, "profile.db")
                .build()
        }.also { instance = it }
    }
}