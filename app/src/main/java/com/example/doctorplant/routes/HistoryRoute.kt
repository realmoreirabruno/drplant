package com.example.doctorplant.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.doctorplant.ui.history.HistoryEvent
import com.example.doctorplant.ui.history.HistoryScreen
import com.example.doctorplant.ui.history.HistoryViewModel
import com.example.doctorplant.utils.navigateToDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryRoute(
    navController: NavController,
    viewModel: HistoryViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HistoryScreen(
        state = uiState,
        onItemClick = { item -> navigateToDetails(navController, item) },
        onEvent = { event ->
            when(event) {
                is HistoryEvent.ChangeFilter -> viewModel.updateFilter(event.filter)
                is HistoryEvent.ToggleSelection -> viewModel.toggleSelection(event.item)
                is HistoryEvent.ClearSelection -> viewModel.clearSelection()
                is HistoryEvent.DeleteSelected -> viewModel.deleteSelectedItems()
            }
        }
    )
}