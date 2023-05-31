package ru.player.sdk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Кастомный player - контроллер с кнопками и слайдерами управления
 * @param onVolumeChanged - отслеживает изменение громкости видео (слайдер)
 * @param onSpeedChanged - отслеживает изменение скорости видео (слайдер)
 * @param volume - начальное значение громкости
 * @param speed - начальное значение скорости видео
 */
@Composable
fun VideoPlayerControlBar(
    onVolumeChanged: (Float) -> Unit,
    onSpeedChanged: (Float) -> Unit,
    volume: Float,
    speed: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Slider(
            value = volume,
            onValueChange = { newVolume -> onVolumeChanged(newVolume) },
            valueRange = 0f..1f,
            steps = 100,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )

        Slider(
            value = speed,
            onValueChange = { newSpeed -> onSpeedChanged(newSpeed) },
            valueRange = 1f..2f,
            steps = 4,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        )
    }
}