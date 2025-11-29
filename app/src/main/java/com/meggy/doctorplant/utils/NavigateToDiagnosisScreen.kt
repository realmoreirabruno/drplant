package com.meggy.doctorplant.utils

import androidx.navigation.NavController
import com.meggy.doctorplant.data.model.DiagnosisHistory
import com.meggy.doctorplant.data.model.DiseaseInformation
import com.meggy.doctorplant.data.model.PlantDisease
import com.google.gson.Gson
import java.net.URLEncoder

fun navigateToDetails(navController: NavController, history: DiagnosisHistory) {
    val diseaseInfo = DiseaseInformation(
        name = history.diseaseName,
        description = history.description,
        symptoms = history.symptoms,
        treatment = history.treatment
    )
    val plantDisease = PlantDisease(
        diagnosis = history.diagnosisStatus,
        technicalId = history.technicalId,
        confidence = history.confidence,
        information = diseaseInfo
    )

    // Transforma em JSON para passar na rota
    val diseaseJson = Gson().toJson(plantDisease)
    val encodedJson = URLEncoder.encode(diseaseJson, "UTF-8")
    val encodedUri = URLEncoder.encode(history.imageUri, "UTF-8")

    // Navega para uma rota especial que vamos criar
    navController.navigate("diagnosis/$encodedUri/$encodedJson")
}