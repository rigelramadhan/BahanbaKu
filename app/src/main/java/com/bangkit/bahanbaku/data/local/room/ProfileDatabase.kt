//package com.bangkit.bahanbaku.data.local.room
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import com.bangkit.bahanbaku.data.remote.response.FoodEntity
//import com.bangkit.bahanbaku.data.remote.response.ProfileEntity
//
//@Database(entities = [ProfileEntity::class], version = 1)
//@TypeConverters(Converters::class)
//abstract class ProfileDatabase : RoomDatabase() {
//    abstract fun profileDao(): ProfileDao
//
//    companion object {
//        @Volatile
//        private var instance: ProfileDatabase? = null
//
//        fun getInstance(context: Context): ProfileDatabase = instance ?: synchronized(this) {
//            instance ?: Room.databaseBuilder(context, ProfileDatabase::class.java, "food.db")
//                .build()
//        }.also { instance = it }
//    }
//}