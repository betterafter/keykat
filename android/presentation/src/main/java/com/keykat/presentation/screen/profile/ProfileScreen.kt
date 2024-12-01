package com.keykat.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.keykat.presentation.profileViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = profileViewModel()
) {
    println("!!!!!!!!!!!!!!!!!!!!!!")
//    LaunchedEffect(key1 = "profileScreen") {
//        viewModel.initTopProfile()
//    }
    
    return Column {
        Text(text = "hello")
    }
}