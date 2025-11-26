package com.example.doctorplant.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.data.repository.DiagnosisRepository
import com.example.doctorplant.utils.TimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HistoryState(
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
    private val _selectedFilter = MutableStateFlow(TimeUtils.HistoryFilter.ALL)
    private val _selectedItems = MutableStateFlow(setOf<DiagnosisHistory>())

    val uiState: StateFlow<HistoryState> = combine(
        repository.getAllHistory(),
        _selectedFilter,
        _selectedItems
    ) { allHistory, filter, selectedItems ->

        val totalScans = allHistory.size
        val totalDiseased = allHistory.count { it.diagnosisStatus != "Saudável" }
        val accuracyRate = calculateAccuracy(allHistory)

        val filteredList = when (filter) {
            TimeUtils.HistoryFilter.ALL -> allHistory
            TimeUtils.HistoryFilter.TODAY -> allHistory.filter { TimeUtils.isToday(it.date) }
            TimeUtils.HistoryFilter.WEEK -> allHistory.filter { TimeUtils.isThisWeek(it.date) }
        }

        HistoryState(
            historyItems = filteredList,
            selectedFilter = filter,
            selectedItems = selectedItems,
            totalScans = totalScans,
            totalDiseased = totalDiseased,
            accuracyRate = accuracyRate
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HistoryState()
    )

    fun updateFilter(filter: TimeUtils.HistoryFilter) {
        _selectedFilter.value = filter
    }

    fun toggleSelection(item: DiagnosisHistory) {
        _selectedItems.update { currentSet ->
            if (currentSet.contains(item)) currentSet - item else currentSet + item
        }
    }

    fun clearSelection() {
        _selectedItems.value = emptySet()
    }

    fun deleteSelectedItems() {
        val itemsToDelete = _selectedItems.value.toList()
        if (itemsToDelete.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteHistoryItems(itemsToDelete)
                // O Room vai emitir a nova lista automaticamente,
                // e o combine vai recalcular o UI State.
                // Só precisamos limpar a seleção visual:
                _selectedItems.value = emptySet()
            }
        }
    }

    private fun calculateAccuracy(items: List<DiagnosisHistory>): Int {
        if (items.isEmpty()) return 0
        return items.map {
            it.confidence
                .replace("%", "")
                .trim()
                .toFloatOrNull()
                ?: 0f
        }.average().toInt()
    }
}
