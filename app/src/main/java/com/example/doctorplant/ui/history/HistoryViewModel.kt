package com.example.doctorplant.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorplant.data.repository.DiagnosisRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(private val repository: DiagnosisRepository) : ViewModel() {
    // Converte o Flow do banco para StateFlow para o Compose usar
    val historyList = repository.getAllHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
