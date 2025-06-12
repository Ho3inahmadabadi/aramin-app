package com.aramin.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sounds")
data class Sound(
    @PrimaryKey val id: Int,
    val title: String,
    val fileName: String,
    val description: String,
    val isPremium: Boolean
)
