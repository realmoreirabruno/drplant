package com.example.doctorplant.routes

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.doctorplant.ui.diagnosis.DiagnosisScreen
import com.example.doctorplant.ui.diagnosis.DiagnosisViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DiagnosisRoute(
    navController: NavController,
    imageUriString: String?,
    viewModel: DiagnosisViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val imageUri = remember(imageUriString) {
        Uri.decode(imageUriString).toUri()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = imageUri) {
        viewModel.diagnosePlant(context, imageUri)
    }

    DiagnosisScreen(
        imageUri = imageUri,
        uiState = uiState,
        onBackClick = { navController.popBackStack() }
    )
}