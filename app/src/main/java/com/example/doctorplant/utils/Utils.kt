package com.example.doctorplant.utils
import java.util.Locale

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
}