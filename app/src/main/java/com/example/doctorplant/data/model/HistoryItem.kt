package com.example.doctorplant.data.model

import androidx.compose.ui.graphics.Color

data class HistoryItem(
    val plantName: String,
    val status: Boolean, // 0 = healthy 1 = diseased
    val disease: String,
    val confidence: Int,
    val advice: String,
    val imageRes: Int,
    val color: Color
)