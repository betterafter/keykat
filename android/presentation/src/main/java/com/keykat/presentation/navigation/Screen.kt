package com.keykat.presentation.navigation

import android.net.Uri

sealed class Screen(
    val route: String
) {
    data object Profile : Screen("profile")
    data object Web : Screen("web?url={url}") {
        fun createRoute(url: String) = "web?url=${Uri.encode(url)}"
    }
}