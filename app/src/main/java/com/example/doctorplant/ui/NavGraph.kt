package com.example.doctorplant.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.doctorplant.routes.DiagnosisRoute
import com.example.doctorplant.routes.HistoryDiagnosisRoute
import com.example.doctorplant.routes.HistoryRoute
import com.example.doctorplant.ui.components.getRouteIndex
import com.example.doctorplant.ui.diagnosis.CameraScreen
import com.example.doctorplant.ui.home.HomeScreen
import com.example.doctorplant.ui.landing.LandingScreen
import com.example.doctorplant.ui.learnmore.LearnMoreScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "landing",
        enterTransition = {
            val fromIndex = getRouteIndex(initialState.destination.route)
            val toIndex = getRouteIndex(targetState.destination.route)

            if (toIndex > fromIndex) {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500))
            } else {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500))
            }
        },
        exitTransition = {
            val fromIndex = getRouteIndex(initialState.destination.route)
            val toIndex = getRouteIndex(targetState.destination.route)

            if (toIndex > fromIndex) {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(500))
            } else {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(500))
            }
        },
        modifier = modifier
    ) {
        composable("landing") { LandingScreen(navController) }
//        composable("login") { LoginScreen(navController) }
//        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("learn_more") { LearnMoreScreen() }
        composable("camera") { CameraScreen(navController) }
        composable("history") {
            HistoryRoute(navController = navController)
        }
        composable(
            route = "diagnosis/{imageUri}",
            arguments = listOf(navArgument("imageUri") { type = NavType.StringType })
        ) { backStackEntry ->
            DiagnosisRoute(
                navController = navController,
                imageUriString = backStackEntry.arguments?.getString("imageUri")
            )
        }
        composable(
            route = "diagnosis/{imageUri}/{plantData}",
            arguments = listOf(
                navArgument("imageUri") { type = NavType.StringType },
                navArgument("plantData") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            HistoryDiagnosisRoute(
                imageUriString = backStackEntry.arguments?.getString("imageUri"),
                plantDataJson = backStackEntry.arguments?.getString("plantData")
            )
        }
    }
}