package com.example.doctorplant.routes

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.doctorplant.data.model.PlantDisease
import com.example.doctorplant.ui.diagnosis.DiagnosisSuccessScreen
import com.google.gson.Gson

@Composable
fun HistoryDiagnosisRoute(
    navController: NavController,
    imageUriString: String?,
    plantDataJson: String?
) {
    // Processa os argumentos recebidos
    val imageUri = remember(imageUriString) {
        imageUriString?.toUri() ?: Uri.EMPTY
    }

    val plantDisease = remember(plantDataJson) {
        try {
            Gson().fromJson(plantDataJson, PlantDisease::class.java)
        } catch (e: Exception) {
            null
        }
    }

    if (plantDisease != null) {
        DiagnosisSuccessScreen(
            imageUri = imageUri,
            data = plantDisease,
            scanTime = "Histórico",
            onBack = { navController.popBackStack() }
        )
    }
}