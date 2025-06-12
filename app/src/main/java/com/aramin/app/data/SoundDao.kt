package com.aramin.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SoundDao {
    @Query("SELECT * FROM sounds")
    suspend fun getSounds(): List<Sound>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sounds: List<Sound>)
}
