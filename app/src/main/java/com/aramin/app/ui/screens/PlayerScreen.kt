package com.aramin.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aramin.app.PlayerViewModel
import com.aramin.app.SoundViewModel
import com.aramin.app.ui.components.BreathingAnimation
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PlayerScreen(navController: NavController, soundId: Int, soundViewModel: SoundViewModel) {
    val context = LocalContext.current
    val playerViewModel: PlayerViewModel = viewModel(factory = PlayerViewModel.factory(context))
    val sounds = soundViewModel.sounds.collectAsState()
    val sound = sounds.value.firstOrNull { it.id == soundId }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(sound?.title ?: "")
        val timerOptions = listOf(15L, 30L, 60L)
        timerOptions.forEach { minutes ->
            Button(onClick = { sound?.let { playerViewModel.playAsset(it.fileName, minutes * 60_000) } }) {
                Text("Play $minutes min")
            }
        }
        val cycleOptions = (1..5)
        cycleOptions.forEach { cycle ->
            val minutes = cycle * 90L
            Button(onClick = { sound?.let { playerViewModel.playAsset(it.fileName, minutes * 60_000) } }) {
                Text("Play $cycle cycle(s)")
            }
        }
        BreathingAnimation()
        Button(onClick = { playerViewModel.stop(); navController.popBackStack() }) {
            Text("Stop")
        }
    }
}
