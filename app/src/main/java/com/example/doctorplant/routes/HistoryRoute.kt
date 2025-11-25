package com.example.doctorplant.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.doctorplant.ui.history.HistoryScreen
import com.example.doctorplant.ui.history.HistoryViewModel
import com.example.doctorplant.utils.navigateToDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryRoute(
    navController: NavController,
    viewModel: HistoryViewModel = koinViewModel()
) {
    // Coleta o estado de forma segura para o ciclo de vida
    val historyItems by viewModel.historyList.collectAsStateWithLifecycle()

    HistoryScreen(
        historyItems = historyItems,
        onItemClick = { item ->
            navigateToDetails(navController, item)
        }
    )
}