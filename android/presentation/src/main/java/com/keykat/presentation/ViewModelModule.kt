package com.keykat.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.keykat.presentation.screen.career.CareerViewModel
import com.keykat.presentation.screen.portfolio.PortfolioViewModel
import com.keykat.presentation.screen.profile.ProfileViewModel

@Composable
fun profileViewModel(): ProfileViewModel = hiltViewModel<ProfileViewModel>()

@Composable
fun careerViewModel(): CareerViewModel = hiltViewModel<CareerViewModel>()

@Composable
fun portfolioViewModel(): PortfolioViewModel = hiltViewModel<PortfolioViewModel>()