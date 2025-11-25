package com.example.doctorplant.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diagnosis_history")
data class DiagnosisHistory(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String,
    val diseaseName: String,     // info.nome
    val diagnosisStatus: String, // info.diagnostico (Saudável/Doente)
    val technicalId: String,     // info.id_tecnico
    val description: String,     // info.descricao
    val treatment: String,       // info.tratamento
    val symptoms: List<String>,  // info.sintomas (Usa o Converter acima)
    val confidence: String,      // Nivel de confiança
    val date: Long = System.currentTimeMillis()
)