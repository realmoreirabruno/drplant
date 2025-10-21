package com.example.doctorplant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnosis_history")
data class DiagnosisHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String,
    val diseaseName: String,
    val probability: Double,
    val date: Long = System.currentTimeMillis()
)