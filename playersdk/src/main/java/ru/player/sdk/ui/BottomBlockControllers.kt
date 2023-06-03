package ru.player.sdk.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.player.sdk.R

@Composable
fun BottomBlockControllers(
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.padding(bottom = 32.dp)) {
        // seek bar
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = 1f,
            onValueChange = { value: Float -> },
            valueRange = 0f..1f,
            colors =
            SliderDefaults.colors(
                thumbColor = Purple40,
                activeTickColor = Purple40
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // show total video time
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Total video duration",
                color = Purple40
            )

            // full screen toggle button
            IconButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {}
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.base_ic_full_screen),
                    contentDescription = "Enter/Exit fullscreen"
                )
            }
        }
    }
}