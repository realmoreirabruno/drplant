package com.example.doctorplant.data.model

data class PlantDisease(
    val name: String,
    val probability: Double,
    val symptoms: String,
    val causes: String,
    val precautions: String
)