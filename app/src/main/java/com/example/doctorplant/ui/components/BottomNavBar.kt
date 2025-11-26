package com.example.doctorplant.ui.components

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.doctorplant.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.doctorplant.ui.theme.BeautifulGreen
import com.example.doctorplant.ui.theme.DoctorPlantTheme

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: Painter
)

fun getRouteIndex(route: String?): Int {
    return when (route) {
        "home" -> 0
        "camera" -> 1
        "history" -> 2
        else -> 0
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val items = listOf(
        BottomNavItem("home", "Home", painterResource(R.drawable.ic_house)),
        BottomNavItem("camera", "Câmera", painterResource(R.drawable.ic_camera)),
        BottomNavItem("history", "Histórico", painterResource(R.drawable.ic_history))
    )

    NavigationBar(
        containerColor = Color(0xFFF8F8F8),
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            if (item.route == "camera") {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(
                        onClick = { navController.navigate("camera") },
                        containerColor = BeautifulGreen
                    ) {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.label,
                            tint = Color.White
                        )
                    }
                }
            } else {
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                popUpTo("home") {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.label
                        )
                    },
                    label = { Text(item.label, color = Color(0xFF000000)) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BeautifulGreen,
                        selectedTextColor = BeautifulGreen,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    DoctorPlantTheme {
        BottomNavBar(navController = rememberNavController())
    }
}