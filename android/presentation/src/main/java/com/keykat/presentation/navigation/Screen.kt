package com.keykat.presentation.navigation

sealed class Screen(
    val route: String
) {
    data object Profile : Screen("profile")
}