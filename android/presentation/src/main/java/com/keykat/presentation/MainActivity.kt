package com.keykat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keykat.presentation.navigation.Graph
import com.keykat.presentation.navigation.MainNavHost
import com.keykat.presentation.navigation.Screen
import com.keykat.presentation.screen.common.navigationHeight
import com.keykat.presentation.ui.theme.KeykatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            WindowInsets.systemBars.only(
                WindowInsetsSides.Top
            )
            Box {
                KeykatTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val navController = rememberNavController()
                        val bottoms = arrayOf(Graph.Profile)

                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val allowBottomNavigationBarList = listOf(
                            Screen.Profile
                        )

                        Scaffold(
                            bottomBar = {
                                val currentDestination = navBackStackEntry?.destination
                                val shouldShowBottomNavigationBar =
                                    allowBottomNavigationBarList.find {
                                        currentDestination?.route == it.route
                                    } != null

                                if (shouldShowBottomNavigationBar) {
                                    NavigationBar(
                                        modifier = Modifier
                                            .height(navigationHeight.dp),
                                        windowInsets = WindowInsets(0.dp)
                                    ) {
                                        bottoms.forEach { screen ->
                                            NavigationBarItem(
                                                icon = {
                                                    screen.icon?.let {
                                                        Icon(
                                                            screen.icon,
                                                            contentDescription = null
                                                        )
                                                    }
                                                },
                                                label = { Text(stringResource(screen.resourceId)) },
                                                selected = currentDestination?.hierarchy?.any {
                                                    it.route == screen.route
                                                } == true,
                                                onClick = {
                                                    navController.navigate(screen.route) {
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        launchSingleTop = true
                                                        restoreState = true
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        ) { innerPadding ->
                            MainNavHost(
                                navController,
                                Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}