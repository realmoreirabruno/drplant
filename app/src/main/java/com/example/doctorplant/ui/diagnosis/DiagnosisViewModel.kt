package com.example.doctorplant.ui.diagnosis

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorplant.data.model.PlantDisease
import com.example.doctorplant.data.repository.DiagnosisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

sealed class DiagnosisUiState {
    object Idle : DiagnosisUiState()
    object Loading : DiagnosisUiState()
    data class Success(val data: PlantDisease) : DiagnosisUiState()
    data class Error(val message: String) : DiagnosisUiState()
}


class DiagnosisViewModel(
    private val repository: DiagnosisRepository
) : ViewModel() {

    // 1. Declaração do Estado usando StateFlow
    // O _uiState é mutável e privado (só o ViewModel altera)
    private val _uiState = MutableStateFlow<DiagnosisUiState>(DiagnosisUiState.Idle)

    // O uiState é imutável e público (a UI só observa/lê)
    val uiState = _uiState.asStateFlow()

    fun diagnosePlant(context: Context, imageUri: Uri) {
        _uiState.value = DiagnosisUiState.Loading

        viewModelScope.launch {
            // Operações de arquivo (uriToFile) e Banco/Rede (repository)
            // devem rodar em Dispatchers.IO para não travar a tela.
            val newState = withContext(Dispatchers.IO) {
                try {
                    val file = uriToFile(context, imageUri)
                    val result = repository.diagnosePlant(file)

                    if (result != null) {
                        DiagnosisUiState.Success(result)
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
}
