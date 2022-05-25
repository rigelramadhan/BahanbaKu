package com.bangkit.bahanbaku.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.bahanbaku.data.remote.response.SupplierEntity

@Database(entities = [SupplierEntity::class], version = 1)
abstract class SupplierDatabase : RoomDatabase() {
    abstract fun supplierDao(): SupplierDao

    companion object {
        @Volatile
        private var instance: SupplierDatabase? = null

        fun getInstance(context: Context): SupplierDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(context, SupplierDatabase::class.java, "supplier.db")
                .build()
        }.also { instance = it }
    }
}