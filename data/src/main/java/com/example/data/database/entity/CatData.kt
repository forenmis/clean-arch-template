package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatData(
    @PrimaryKey
    val id: String,
    val image: String,
)
