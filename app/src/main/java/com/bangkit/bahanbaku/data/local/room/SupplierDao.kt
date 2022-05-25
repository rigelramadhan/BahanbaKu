package com.bangkit.bahanbaku.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bangkit.bahanbaku.data.remote.response.SupplierByIdResponse
import com.bangkit.bahanbaku.data.remote.response.SupplierEntity
import com.bangkit.bahanbaku.data.remote.response.SupplierResponse

@Dao
interface SupplierDao {
    @Query("SELECT * FROM supplier")
    fun getSuppliers(): LiveData<List<SupplierResponse>>

    @Query("SELECT * FROM supplier WHERE id = :id")
    fun getSupplierById(id: String): LiveData<SupplierByIdResponse>

    @Query("DELETE FROM supplier")
    fun deleteAllSuppliers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSuppliers(suppliers: List<SupplierEntity>)
}