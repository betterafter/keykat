package com.keykat.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.widget.profile.ProfileWidget

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = profileViewModel()
) {
    val profileState by viewModel.profile.collectAsState()
    val scrollState = rememberScrollState()
    LaunchedEffect(viewModel) {
        viewModel.initTopProfile()
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = 50.dp)
    ) {
        Row {
            println("[keykat] $profileState")
            when (profileState) {
                is ProfileUiState.Success -> {
                    val entity = (profileState as ProfileUiState.Success).profileEntity
                    ProfileWidget(
                        navController = navController,
                        profileEntity = entity,
                        scrollState = scrollState
                    )
                }

                is ProfileUiState.Error -> {

                }

                is ProfileUiState.Init -> {

                }

                is ProfileUiState.Loading -> {

                }
            }
        }
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Blue))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Red))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Green))
        Box(modifier = Modifier.width(100.dp).height(100.dp).background(Color.Gray))
    }
}
