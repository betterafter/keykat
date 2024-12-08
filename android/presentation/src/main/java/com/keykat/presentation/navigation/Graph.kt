package com.keykat.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.WorkHistory
import androidx.compose.ui.graphics.vector.ImageVector
import com.keykat.presentation.R

sealed class Graph(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector? = null,
    val selectedIcon: ImageVector? = null
) {
    data object Profile : Graph(
        "graph_profile",
        R.string.profile,
        Icons.Outlined.AccountCircle,
        Icons.Filled.AccountCircle,
    )

    data object Career: Graph(
        "graph_career",
        R.string.career,
        Icons.Outlined.WorkHistory,
        Icons.Filled.WorkHistory
    )

    data object Portfolio: Graph(
        "graph_portfolio",
        R.string.portfolio,
        Icons.Outlined.Collections,
        Icons.Filled.Collections
    )

    data object Web : Graph(
        route = "graph_web",
        R.string.web
    )
}