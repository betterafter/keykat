package com.keykat.presentation.screen.profile

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.ProfileEntity

@Composable
fun SuccessProfileWidget(
    profileEntity: ProfileEntity,
    scrollState: ScrollState
) {
    val namePosition by animateIntOffsetAsState(
        targetValue = IntOffset(scrollState.value / 3, 0),
        label = ""
    )
    val contactPosition by animateIntOffsetAsState(
        targetValue = IntOffset(scrollState.value / 2, 0),
        label = ""
    )
    val density = LocalDensity.current

    Column {
        Row {
            AsyncImage(
                model = profileEntity.profileUrl,
                contentDescription = "profile",
                modifier = Modifier
                    .padding(10.dp, 10.dp, 30.dp, 10.dp)
                    .width(120.dp)
                    .height(120.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = profileEntity.name.toString(),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .offset { namePosition }
                )
                Box(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .offset { contactPosition }
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = "이메일 아이콘",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(2.dp)
                        )
                        Text(
                            text = profileEntity.email.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Rounded.PhoneAndroid,
                            contentDescription = "이메일 아이콘",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(2.dp)

                        )
                        Text(
                            text = profileEntity.tel.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}