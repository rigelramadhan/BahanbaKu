package com.bangkit.bahanbaku.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.bahanbaku.data.remote.response.ProfileEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile WHERE id = :id")
    fun getProfile(id: String): LiveData<List<ProfileEntity>>

    @Query("DELETE FROM profile")
    fun deleteAllProfile()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: ProfileEntity)
}