package com.keykat.presentation.screen.profile.widget.profile

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.ProfileEntity

@Composable
fun ProfileIntroduceWidget(
    profileEntity: ProfileEntity,
    scrollState: ScrollState
) {
    val introducePosition by animateIntOffsetAsState(
        targetValue = IntOffset(-scrollState.value / 2, 0),
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