package com.aramin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aramin.app.navigation.AraminNavHost
import com.aramin.app.ui.theme.AraminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AraminTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val soundViewModel: SoundViewModel = viewModel(factory = SoundViewModel.factory(this))
                    AraminNavHost(soundViewModel)
                }
            }
        }
    }
}
