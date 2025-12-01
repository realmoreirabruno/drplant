package com.meggy.doctorplant

import androidx.compose.foundation.layout.PaddingValues
import com.meggy.doctorplant.ui.AppNavGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.meggy.doctorplant.ui.components.BottomNavBar
import com.meggy.doctorplant.ui.components.TopBar
import com.meggy.doctorplant.ui.theme.DoctorPlantTheme

@Composable
fun MainScreen(
    navController: androidx.navigation.NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        "history",
        "home"
    )

    val topBarRoutes = listOf(
        "diagnosis"
    )

    val fullScreenRoutes = listOf(
        "landing",
        "home",
        "history"
    )

    Scaffold(
        topBar = {
            if (currentRoute in topBarRoutes) {
                TopBar(
                    title = when (currentRoute) {
                        "diagnosis" -> "Diagnosis"
                        else -> ""
                    },
                    onNavigationClick = { navController.popBackStack() }
                )
            }
        },
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        val targetPadding = if (currentRoute in fullScreenRoutes) {
            PaddingValues(0.dp)
        } else {
            innerPadding
        }

        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(targetPadding)
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
