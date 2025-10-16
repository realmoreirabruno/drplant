package com.example.doctorplant.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex


@Composable
fun GenericLoading() {
    Box(
        Modifier
            .height(4.dp)
            .zIndex(1f)
    ) {
        LinearProgressIndicator(
            color = colorScheme.secondary,
            trackColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
