package com.aramin.app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aramin.app.data.Sound
import com.aramin.app.data.SoundRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SoundViewModel(private val repo: SoundRepository) : ViewModel() {
    val repository: SoundRepository
        get() = repo
    private val _sounds = MutableStateFlow<List<Sound>>(emptyList())
    val sounds: StateFlow<List<Sound>> = _sounds

    init {
        viewModelScope.launch {
            _sounds.value = repo.getSounds()
        }
    }

    fun unlockPremium() {
        repo.isPremiumUnlocked = true
    }

    companion object {
        fun factory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SoundViewModel(SoundRepository.getInstance(context)) as T
                }
            }
    }
}
