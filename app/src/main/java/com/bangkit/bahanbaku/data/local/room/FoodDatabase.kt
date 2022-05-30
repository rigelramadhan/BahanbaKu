//package com.bangkit.bahanbaku.data.local.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.bangkit.bahanbaku.data.remote.response.FoodEntity
//
//@Database(entities = [FoodEntity::class], version = 1)
//abstract class FoodDatabase : RoomDatabase() {
//    abstract fun foodDao(): FoodDao
//
//    companion object {
//        @Volatile
//        private var instance: FoodDatabase? = null
//
//        fun getInstance(context: Context): FoodDatabase = instance ?: synchronized(this) {
//            instance ?: Room.databaseBuilder(context, FoodDatabase::class.java, "foods.db")
//                .build()
//        }.also { instance = it }
//    }
//}