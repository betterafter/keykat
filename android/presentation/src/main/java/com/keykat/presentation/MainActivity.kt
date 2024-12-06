package com.keykat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                        val bottoms = arrayOf(
                            Graph.Career,
                            Graph.Profile,
                            Graph.Portfolio
                        )

                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val allowBottomNavigationBarList = listOf(
                            Screen.Profile,
                            Screen.Portfolio,
                            Screen.Career
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
                                            .height(navigationHeight.dp)
                                            .background(Color.Transparent),
                                        windowInsets = WindowInsets(0.dp)
                                    ) {
                                        bottoms.forEach { screen ->
                                            val current = currentDestination?.hierarchy?.any {
                                                it.route == screen.route
                                            } == true

                                            NavigationBarItem(
                                                icon = {
                                                    screen.icon?.let {
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Icon(
                                                                screen.icon,
                                                                contentDescription = null,
                                                                modifier = Modifier.size(
                                                                    if (current) {
                                                                        18.dp
                                                                    } else {
                                                                        20.dp
                                                                    }
                                                                )
                                                            )

                                                            if (current) {
                                                                Spacer(modifier = Modifier.width(4.dp))
                                                                Text(
                                                                    text = stringResource(
                                                                        screen.resourceId,
                                                                    ),
                                                                    textAlign = TextAlign.Center,
                                                                    style = MaterialTheme.typography.labelSmall
                                                                )
                                                            }
                                                        }
                                                    }
                                                },
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