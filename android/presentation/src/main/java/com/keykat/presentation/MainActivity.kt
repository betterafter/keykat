package com.keykat.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.keykat.presentation.navigation.Graph
import com.keykat.presentation.navigation.MainNavHost
import com.keykat.presentation.screen.profile.ProfileViewModel
import com.keykat.presentation.ui.theme.KeykatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v: View?, _: WindowInsetsCompat? ->
//            ViewCompat.onApplyWindowInsets(
//                v!!, WindowInsetsCompat.CONSUMED
//            )
//        }

        setContent {
            KeykatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val bottoms = arrayOf(Graph.Profile)

                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                bottoms.forEach { screen ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                screen.icon,
                                                contentDescription = null
                                            )
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