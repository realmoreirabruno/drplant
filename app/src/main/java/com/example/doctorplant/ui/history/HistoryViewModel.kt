package com.example.doctorplant.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.data.repository.DiagnosisRepository
import com.example.doctorplant.utils.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HistoryUiState(
    val historyItems: List<DiagnosisHistory> = emptyList(),
    val selectedFilter: TimeUtils.HistoryFilter = TimeUtils.HistoryFilter.ALL,
    val selectedItems: Set<DiagnosisHistory> = emptySet(),
    val totalScans: Int = 0,
    val totalDiseased: Int = 0,
    val accuracyRate: Int = 0
) {
    val isSelectionMode: Boolean get() = selectedItems.isNotEmpty()
}
class HistoryViewModel(private val repository: DiagnosisRepository) : ViewModel() {
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
