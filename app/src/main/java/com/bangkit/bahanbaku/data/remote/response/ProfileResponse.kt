package com.bangkit.bahanbaku.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ProfileResponse(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "photoUrl")
    val photoUrl: String
)
