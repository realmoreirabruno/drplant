package com.meggy.doctorplant.utils

import java.util.Locale
import java.util.Calendar
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

object TimeUtils {
    fun customFormatDuration(millis: Long): String {
        if (millis < 60000) {
            val seconds = millis / 1000f
            return String.format(Locale.US, "%.1fs", seconds)
        }
        else {
            val totalSeconds = millis / 1000
            val minutes = totalSeconds / 60
            val remainingSeconds = totalSeconds % 60
            return "${minutes}m ${remainingSeconds}s"
        }
    }

    enum class HistoryFilter {
        ALL, TODAY, WEEK
    }

    fun isToday(dateMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        val todayYear = calendar.get(Calendar.YEAR)
        val todayDay = calendar.get(Calendar.DAY_OF_YEAR)

        calendar.timeInMillis = dateMillis
        return calendar.get(Calendar.YEAR) == todayYear &&
                calendar.get(Calendar.DAY_OF_YEAR) == todayDay
    }

    fun isThisWeek(dateMillis: Long): Boolean {
        val calendar = Calendar.getInstance()
        val currentWeek = calendar.get(Calendar.WEEK_OF_YEAR)
        val currentYear = calendar.get(Calendar.YEAR)

        calendar.timeInMillis = dateMillis
        return calendar.get(Calendar.YEAR) == currentYear &&
                calendar.get(Calendar.WEEK_OF_YEAR) == currentWeek
    }

    fun Modifier.onHorizontalSwipe(
        onSwipeLeft: () -> Unit = {},
        onSwipeRight: () -> Unit = {},
        threshold: Float = 200f
    ): Modifier = composed {
        var offsetX by remember { mutableFloatStateOf(0f) }

        pointerInput(Unit) {
            detectHorizontalDragGestures(
                onDragEnd = {
                    if (offsetX > threshold) {
                        onSwipeRight()
                    } else if (offsetX < -threshold) {
                        onSwipeLeft()
                    }
                    offsetX = 0f
                },
                onHorizontalDrag = { _, dragAmount ->
                    offsetX += dragAmount
                }
            )
        }
    }

    fun safeDecode(text: String): String {
        return try {
            URLDecoder.decode(text, StandardCharsets.UTF_8.toString())
        } catch (e: Exception) {
            text.replace("+", " ")
        }
    }
}