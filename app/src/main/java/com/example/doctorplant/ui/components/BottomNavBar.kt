package com.example.doctorplant.ui.components

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.doctorplant.R
import com.example.doctorplant.ui.theme.GreenHome
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
import com.example.doctorplant.ui.theme.DoctorPlantTheme

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: Painter
)

@Composable
fun BottomNavBar(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val items = listOf(
        BottomNavItem("home", "Home", painterResource(R.drawable.ic_house)),
        BottomNavItem("camera", "Camera", painterResource(R.drawable.ic_camera)),
        BottomNavItem("history", "History", painterResource(R.drawable.ic_history))
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
                        containerColor = GreenHome,
                        elevation = FloatingActionButtonDefaults.elevation(8.dp)
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
                        navController.navigate(item.route) {
                            popUpTo("home")
                            launchSingleTop = true
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
                        selectedIconColor = GreenHome,
                        selectedTextColor = GreenHome,
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