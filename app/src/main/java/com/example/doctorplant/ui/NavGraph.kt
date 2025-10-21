package com.example.doctorplant.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.example.doctorplant.ui.diagnosis.CameraScreen
import com.example.doctorplant.ui.diagnosis.DiagnosisScreen
import com.example.doctorplant.ui.history.HistoryScreen
import com.example.doctorplant.ui.home.HomeScreen
import com.example.doctorplant.ui.landing.LandingScreen
import com.example.doctorplant.ui.login.LoginScreen
import com.example.doctorplant.ui.register.RegisterScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "landing"
    ) {
        composable("landing") { LandingScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
//        composable("camera") { CameraScreen(navController) }
        composable("diagnosis") { DiagnosisScreen(navController) }
        composable("history") { HistoryScreen(navController) }
    }
}