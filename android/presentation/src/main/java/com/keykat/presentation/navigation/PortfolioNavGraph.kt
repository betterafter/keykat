package com.keykat.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.keykat.presentation.screen.portfolio.PortfolioScreen

fun NavGraphBuilder.portfolioNavGraph(
    navController: NavController,
) {
    navigation(
        startDestination = Screen.Portfolio.route,
        route = Graph.Portfolio.route,
    ) {
        composable(
            route = Screen.Portfolio.route,
        ) {
            PortfolioScreen(
                navController = navController
            )
        }
    }
}