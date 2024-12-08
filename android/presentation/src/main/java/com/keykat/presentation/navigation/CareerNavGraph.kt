package com.keykat.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keykat.presentation.screen.career.CareerScreen
import com.keykat.presentation.screen.profile.ProfileScreen

fun NavGraphBuilder.careerNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Career.route,
        route = Graph.Career.route,
    ) {
        composable(
            route = Screen.Career.route,
        ) {
            CareerScreen(
                navController = navController
            )
        }
    }
}