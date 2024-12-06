package com.keykat.presentation.navigation

import android.net.Uri

sealed class Screen(
    val route: String
) {
    data object Profile : Screen("profile")
    data object Career : Screen("career")
    data object Portfolio : Screen("portfolio")
    data object Web : Screen("web?url={url}") {
        fun createRoute(url: String) = "web?url=${Uri.encode(url)}"
    }
}