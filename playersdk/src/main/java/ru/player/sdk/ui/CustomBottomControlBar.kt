package ru.player.sdk.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import ru.player.sdk.R

@Composable
fun CustomBottomControlBar(
    onFullscreenClicked: () -> Unit
) {
    var isFullscreenState by remember { mutableStateOf(false) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleObserver = rememberFullscreenObserver { newState ->
        isFullscreenState = newState
    }

    lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = { onFullscreenClicked() }
            ) {
                val fullscreenIcon = if (isFullscreenState) {
                    painterResource(id = R.drawable.base_ic_exit_full_screen)
                } else {
                    painterResource(id = R.drawable.base_ic_full_screen)
                }
                Icon(
                    painter = fullscreenIcon,
                    contentDescription = "Fullscreen"
                )
            }
        }
    }
}

@Composable
private fun rememberFullscreenObserver(
    onFullscreenChanged: (Boolean) -> Unit
): LifecycleEventObserver {
    return remember(onFullscreenChanged) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> onFullscreenChanged(true)
                Lifecycle.Event.ON_PAUSE -> onFullscreenChanged(false)
                else -> Unit
            }
        }
    }
}