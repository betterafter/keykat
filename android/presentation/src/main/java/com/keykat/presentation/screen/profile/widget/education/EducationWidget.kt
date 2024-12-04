package com.keykat.presentation.screen.profile.widget.education

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.ProfileViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EducationWidget(
    educationEntity: List<EducationEntity>,
    currentIndex: Int,
    viewModel: ProfileViewModel = profileViewModel(),
) {
    val scrollState = viewModel.getScrollState()

    val density = LocalDensity.current
    var titleVisible by remember {
        mutableStateOf(Array(educationEntity.size) { i -> false })
    }
    var contentVisible by remember {
        mutableStateOf(Array(educationEntity.size) { i -> false })
    }

    LaunchedEffect(Unit) {
        snapshotFlow { scrollState?.currentPage }.collect { page ->
            if (page == currentIndex) {
                titleVisible.clone().mapIndexed { index, b ->
                    val titleCopy = titleVisible.clone()
                    titleCopy[index] = true
                    titleVisible = titleCopy
                    delay(200)

                    val contentCopy = contentVisible.clone()
                    contentCopy[index] = true
                    contentVisible = contentCopy
                    delay(400)
                }
            } else {
                titleVisible = Array(educationEntity.size) { i -> false }
                contentVisible = Array(educationEntity.size) { i -> false }
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "EDUCATION",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(30.dp))
        educationEntity.mapIndexed { index, it ->

            // 타이틀 영역
            AnimatedVisibility(
                visible = titleVisible[index],
                enter = slideInVertically {
                    with(density) { 40.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Bottom
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Column {
                    Text(
                        text = it.name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,

                        )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.where,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall,

                            )
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(12.dp)
                                .padding(start = 4.dp, end = 4.dp)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Text(
                            text = it.duration,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // content 영역
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = contentVisible[index],
                enter = slideInVertically {
                    with(density) { 80.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Bottom
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Text(
                    text = it.content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(42.dp))
        }
    }
}
