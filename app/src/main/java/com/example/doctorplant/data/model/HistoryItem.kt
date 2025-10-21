package com.example.doctorplant.data.model

import androidx.compose.ui.graphics.Color

data class HistoryItem(
    val plantName: String,
    val status: String,
    val disease: String,
    val confidence: Int,
    val advice: String,
    val time: String,
    val imageRes: Int,
    val color: Color
)