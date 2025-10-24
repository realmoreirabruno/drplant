package com.example.doctorplant

import com.example.doctorplant.ui.AppNavGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.doctorplant.ui.components.BottomNavBar
import com.example.doctorplant.ui.components.TopBar
import com.example.doctorplant.ui.theme.DoctorPlantTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        "history",
        "home"
    )

    val topBarRoutes = listOf(
        "diagnosis",
        "history"
    )

    Scaffold(
        topBar = {
            if (currentRoute in topBarRoutes) {
                TopBar(
                    title = when (currentRoute) {
                        "diagnosis" -> "Diagnosis"
                        "history" -> "History"
                        else -> ""
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
        },
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    DoctorPlantTheme {
        MainScreen()
    }
}
