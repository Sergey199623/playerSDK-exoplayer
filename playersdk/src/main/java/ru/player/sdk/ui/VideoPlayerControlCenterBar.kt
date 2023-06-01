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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.player.sdk.R

@Composable
fun CustomVideoPlayerControlCenterBar(
    isPlaying : Boolean,
    onChangePlay: () -> Unit,
    onSeekForwardClicked: () -> Unit,
    onSeekBackwardClicked: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onSeekBackwardClicked) {
                Icon(
                    painter = painterResource(id = R.drawable.base_ic_seek_backward),
                    contentDescription = "SeekBackward",
                    tint = Color.White // Указываем белый цвет для иконки
                )
            }

            IconButton(onClick = onChangePlay) {
                val playingIcon = if (isPlaying) {
                    painterResource(id = R.drawable.base_ic_pause)
                } else {
                    painterResource(id = R.drawable.base_ic_play_arrow)
                }
                Icon(
                    painter = playingIcon,
                    contentDescription = "Play",
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