package com.aramin.app.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aramin.app.SoundViewModel
import com.aramin.app.data.Sound
import com.aramin.app.navigation.Routes
import com.aramin.app.ui.components.SoundCard

@Composable
fun MainScreen(navController: NavController, viewModel: SoundViewModel) {
    val sounds = viewModel.sounds
    val showPremiumDialog = remember { mutableStateOf<Sound?>(null) }

    sounds.value.let { list ->
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(list) { sound ->
                val locked = sound.isPremium && !viewModel.repository.isPremiumUnlocked
                SoundCard(sound, locked) {
                    if (locked) {
                        showPremiumDialog.value = sound
                    } else {
                        navController.navigate("${Routes.Player}/${sound.id}")
                    }
                }
            }
        }
    }

    showPremiumDialog.value?.let { _ ->
        AlertDialog(
            onDismissRequest = { showPremiumDialog.value = null },
            title = { Text("Premium Sound") },
            text = { Text("This is a premium sound. Subscribe to access.") },
            confirmButton = {
                Button(onClick = { viewModel.unlockPremium(); showPremiumDialog.value = null }) {
                    Text("Unlock")
                }
            },
            dismissButton = {
                Button(onClick = { showPremiumDialog.value = null }) { Text("Cancel") }
            }
        )
    }
}
