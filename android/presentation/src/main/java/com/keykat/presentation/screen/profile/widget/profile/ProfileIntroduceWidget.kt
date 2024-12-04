package com.keykat.presentation.screen.profile.widget.profile

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.ProfileViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileIntroduceWidget(
    profileEntity: ProfileEntity,
    viewModel: ProfileViewModel = profileViewModel()
) {
    val scrollState = viewModel.getScrollState()
    var introducePosX by remember { mutableIntStateOf(0) }

    LaunchedEffect(scrollState?.currentPageOffsetFraction) {
        if (scrollState != null) {
            val position = scrollState.getOffsetFractionForPage(0)
            introducePosX = -(position * 100 * 5).toInt()
        }
    }

    val introducePosition by animateIntOffsetAsState(
        targetValue = IntOffset(introducePosX, 0),
        label = ""
    )

    Text(
        text = profileEntity.introduce.toString(),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .offset { introducePosition }
    )
}