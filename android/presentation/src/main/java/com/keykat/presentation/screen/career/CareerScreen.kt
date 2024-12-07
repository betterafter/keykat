package com.keykat.presentation.screen.career

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun CareerScreen(
    navController: NavController,
    careerViewModel: CareerViewModel = com.keykat.presentation.careerViewModel()
) {
    LaunchedEffect(Unit) {
        careerViewModel.getCareer()
    }
}