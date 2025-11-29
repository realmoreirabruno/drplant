package com.meggy.doctorplant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnosis_history")
data class DiagnosisHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String,
    val diseaseName: String,
    val diagnosisStatus: String,
    val technicalId: String,
    val description: String,
    val treatment: String,
    val symptoms: List<String>,
    val confidence: String,
    val date: Long = System.currentTimeMillis()
)