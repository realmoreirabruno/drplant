package com.meggy.doctorplant.routes

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import com.meggy.doctorplant.data.model.PlantDisease
import com.meggy.doctorplant.ui.diagnosis.DiagnosisSuccessScreen
import com.google.gson.Gson

@Composable
fun HistoryDiagnosisRoute(
    imageUriString: String?,
    plantDataJson: String?
) {
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
            isFromHistory = true
        )
    }
}