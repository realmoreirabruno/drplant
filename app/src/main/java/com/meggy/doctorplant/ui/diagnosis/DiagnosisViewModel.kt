package com.meggy.doctorplant.ui.diagnosis

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meggy.doctorplant.data.model.DiagnosisHistory
import com.meggy.doctorplant.data.model.PlantDisease
import com.meggy.doctorplant.data.repository.DiagnosisRepository
import com.meggy.doctorplant.utils.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

sealed class DiagnosisUiState {
    object Idle : DiagnosisUiState()
    object Loading : DiagnosisUiState()
    data class Success(
        val data: PlantDisease,
        val scanTime: String
    ) : DiagnosisUiState()
    data class Error(val message: String) : DiagnosisUiState()
}

class DiagnosisViewModel(
    private val repository: DiagnosisRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<DiagnosisUiState>(DiagnosisUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun diagnosePlant(context: Context, imageUri: Uri) {
        _uiState.value = DiagnosisUiState.Loading

        // 1. Marca o tempo de início
        val startTime = System.currentTimeMillis()

        viewModelScope.launch {
            val newState = withContext(Dispatchers.IO) {
                try {
                    val file = uriToFile(context, imageUri)
                    val result = repository.diagnosePlant(file)

                    if (result != null) {
                        saveToHistory(imageUri, result)

                        val endTime = System.currentTimeMillis()
                        val diff = endTime - startTime
                        val formattedTime = TimeUtils.customFormatDuration(diff)

                        DiagnosisUiState.Success(result, formattedTime)
                    } else {
                        DiagnosisUiState.Error("Erro no diagnóstico")
                    }
                } catch (e: Exception) {
                    DiagnosisUiState.Error(e.message ?: "Erro desconhecido")
                }
            }

            _uiState.value = newState
        }
    }

    private fun uriToFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val tempFile = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
        inputStream?.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return tempFile
    }

    private fun saveToHistory(uri: Uri, disease: PlantDisease) {
        viewModelScope.launch(Dispatchers.IO) {
            val historyItem = DiagnosisHistory(
                imageUri = uri.toString(),
                diseaseName = disease.information.name,
                diagnosisStatus = disease.diagnosis,
                technicalId = disease.technicalId,
                description = disease.information.description,
                treatment = disease.information.treatment,
                symptoms = disease.information.symptoms,
                confidence = disease.confidence
            )
            repository.saveHistory(historyItem)
        }
    }
}