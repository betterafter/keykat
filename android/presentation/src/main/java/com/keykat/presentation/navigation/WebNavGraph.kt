package com.keykat.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.keykat.presentation.screen.web.WebViewScreen

fun NavGraphBuilder.webNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Web.route,
        route = Graph.Web.route
    ) {
        composable(
            route = Screen.Web.route,
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) { entry ->
            val url = entry.arguments?.getString("url")
            WebViewScreen(
                navController = navController,
                url = url ?: ""
            )
        }
    }
}