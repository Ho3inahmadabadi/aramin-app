package com.aramin.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aramin.app.data.Sound

@Composable
fun SoundCard(sound: Sound, locked: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(sound.title, style = MaterialTheme.typography.titleMedium)
            Text(sound.description, style = MaterialTheme.typography.bodySmall)
            if (locked) {
                Icon(Icons.Default.Lock, contentDescription = null)
            }
        }
    }
}
