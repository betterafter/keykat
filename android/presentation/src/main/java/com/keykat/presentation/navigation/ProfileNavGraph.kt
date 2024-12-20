package com.keykat.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keykat.presentation.screen.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Profile.route,
        route = Graph.Profile.route,
        ) {
        composable(
            route = Screen.Profile.route,
        ) {
            ProfileScreen(
                navController = navController
            )
        }
    }
}