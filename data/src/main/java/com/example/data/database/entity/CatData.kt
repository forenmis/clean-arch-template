package com.example.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_images")
data class CatData(
    @PrimaryKey
    val id: String,
    val image: String,
)
