package ru.player.sdk.ui

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable

@SuppressLint("InlinedApi")
@Composable
fun PictureInPictureControls(
    onChangePipMode: () -> Unit
) {
    IconButton(onClick = onChangePipMode) {
        Icon(Icons.Default.ExitToApp, contentDescription = "Выйти из режима Picture-in-Picture")
    }
}