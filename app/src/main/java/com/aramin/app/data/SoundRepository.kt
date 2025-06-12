package com.aramin.app.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SoundRepository private constructor(private val dao: SoundDao) {
    var isPremiumUnlocked: Boolean = false

    suspend fun getSounds(): List<Sound> = dao.getSounds()

    companion object {
        @Volatile
        private var INSTANCE: SoundRepository? = null

        fun getInstance(context: Context): SoundRepository {
            return INSTANCE ?: synchronized(this) {
                val db = AppDatabase.getInstance(context)
                val repo = SoundRepository(db.soundDao())
                CoroutineScope(Dispatchers.IO).launch {
                    if (db.soundDao().getSounds().isEmpty()) {
                        db.soundDao().insertAll(dummySounds())
                    }
                }
                INSTANCE = repo
                repo
            }
        }

        fun dummySounds() = listOf(
            Sound(1, "Rain", "sounds/rain.mp3", "Relaxing rain sound", false),
            Sound(2, "Sea", "sounds/sea.mp3", "Calming sea waves", false),
            Sound(3, "Forest", "sounds/forest.mp3", "Peaceful forest ambience", true),
            Sound(4, "Fire", "sounds/fire.mp3", "Cozy campfire", true),
            Sound(5, "Wind", "sounds/wind.mp3", "Gentle wind", false)
        )
    }
}
