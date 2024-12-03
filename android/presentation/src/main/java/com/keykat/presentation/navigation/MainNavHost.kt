package com.keykat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.ProfileViewModel

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Graph.Profile.route,
        modifier = modifier
    ) {
        profileNavGraph(
            navController = navController
        )

        webNavGraph(
            navController = navController
        )
    }
}