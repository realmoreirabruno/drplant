package com.example.doctorplant.utils

import java.util.Locale
import java.util.Calendar

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
}