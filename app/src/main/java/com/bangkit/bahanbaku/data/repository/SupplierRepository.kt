package com.bangkit.bahanbaku.data.repository

import com.bangkit.bahanbaku.data.local.room.SupplierDatabase
import com.bangkit.bahanbaku.data.remote.retrofit.ApiService
import javax.inject.Inject

class SupplierRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: SupplierDatabase
){

}