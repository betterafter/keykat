package com.keykat.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.ProfileScreen
import com.keykat.presentation.screen.profile.ProfileViewModel
import com.keykat.presentation.screen.web.WebViewScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Profile.route,
        route = Graph.Profile.route
    ) {
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.Web.route,
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val url = entry.arguments?.getString("url")
            WebViewScreen(
                navController = navController,
                url = url ?: ""
            )
        }
    }
}