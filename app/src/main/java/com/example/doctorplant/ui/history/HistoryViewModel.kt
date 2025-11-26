package com.example.doctorplant.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.data.repository.DiagnosisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: DiagnosisRepository) : ViewModel() {
    // Converte o Flow do banco para StateFlow para o Compose usar
    val historyList = repository.getAllHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteItems(items: List<DiagnosisHistory>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHistoryItems(items)
        }
    }
}
