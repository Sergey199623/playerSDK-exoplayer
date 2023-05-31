package ru.player.sdk.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.player.sdk.R

@Composable
fun CustomVideoPlayerControlCenterBar(
    onPlayClicked: () -> Unit,
    onPauseClicked: () -> Unit,
    onStopClicked: () -> Unit,
    onSeekForwardClicked: () -> Unit,
    onSeekBackwardClicked: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
//            .fillMaxSize()
//            .layout { measurable, constraints ->
//                val placeable = measurable.measure(constraints)
//                layout(constraints.maxWidth, constraints.maxHeight) {
//                    val x = (constraints.maxWidth - placeable.width) / 2
//                    val y = (constraints.maxHeight - placeable.height) / 2
//                    placeable.placeRelative(x, y)
//                }
//            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onSeekBackwardClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.base_ic_seek_backward),
                    contentDescription = "SeekBackward",
                    tint = Color.White // Указываем белый цвет для иконки
                )
            }
            IconButton(onClick = onPlayClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.base_ic_play_arrow),
                    contentDescription = "Play",
                    tint = Color.White // Указываем белый цвет для иконки
                )
            }
            IconButton(onClick = onPauseClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.base_ic_pause),
                    contentDescription = "Pause",
                    tint = Color.White // Указываем белый цвет для иконки
                )
            }
            IconButton(onClick = onStopClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.base_ic_stop),
                    contentDescription = "Stop",
                    tint = Color.White // Указываем белый цвет для иконки
                )
            }
            IconButton(onClick = onSeekForwardClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.base_ic_seek_forward),
                    contentDescription = "SeekForward",
                    tint = Color.White // Указываем белый цвет для иконки
                )
            }
        }
    }
}