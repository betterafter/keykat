package com.keykat.presentation.screen.profile.widget.profile

import ProfileTopWidget

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.keykat.domain.profile.entity.ProfileEntity

@Composable
fun ProfileWidget(
    navController: NavController,
    profileEntity: ProfileEntity,
    scrollState: ScrollState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileTopWidget(
            navController = navController,
            profileEntity = profileEntity,
            scrollState = scrollState
        )
        ProfileIntroduceWidget(
            profileEntity = profileEntity,
            scrollState = scrollState
        )
    }
}