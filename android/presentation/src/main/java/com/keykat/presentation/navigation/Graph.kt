package com.keykat.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.keykat.presentation.R

sealed class Graph(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector? = null
) {
    data object Profile : Graph(
        "graph_profile",
        R.string.profile,
        Icons.Filled.Person
    )

    data object Web : Graph(
        route = "graph_web",
        R.string.web
    )
}