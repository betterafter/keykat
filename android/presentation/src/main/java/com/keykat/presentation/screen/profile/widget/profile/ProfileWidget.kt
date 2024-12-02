package com.keykat.presentation.screen.profile.widget.profile

import ProfileTopWidget

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.keykat.domain.profile.entity.ProfileEntity

@Composable
fun ProfileWidget(
    profileEntity: ProfileEntity,
    scrollState: ScrollState
) {
    Column {
        Row {
            ProfileTopWidget(
                profileEntity = profileEntity,
                scrollState = scrollState
            )
        }
        ProfileIntroduceWidget(
            profileEntity = profileEntity,
            scrollState = scrollState
        )
    }
}