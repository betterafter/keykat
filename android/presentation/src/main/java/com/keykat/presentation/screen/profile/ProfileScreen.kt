package com.keykat.presentation.screen.profile

import android.annotation.SuppressLint
import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.common.navigationHeight
import com.keykat.presentation.screen.common.toDp
import com.keykat.presentation.screen.common.toPx
import com.keykat.presentation.screen.profile.widget.education.EducationWidget
import com.keykat.presentation.screen.profile.widget.profile.ProfileWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


@SuppressLint("ReturnFromAwaitPointerEventScope")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = profileViewModel()
) {
    val topProfileState by viewModel.topProfile.collectAsState()
    val bottomProfileState by viewModel.bottomProfile.collectAsState()


    val scrollState = rememberScrollState()
    val deviceHeight = Resources.getSystem().displayMetrics.heightPixels
    var currentSection by remember { mutableIntStateOf(0) }
    val sectionHeight = deviceHeight - navigationHeight.dp.toPx.value
    val sectionPositions = listOf(0F, sectionHeight, sectionHeight * 2)
    val scope = rememberCoroutineScope()

    var shouldMove by remember { mutableStateOf(false) }

    LaunchedEffect(scrollState.isScrollInProgress) {
        val current = scrollState.value
        val isSection = sectionPositions.find {
            current == it.toInt()
        } != null

        if (isSection) {
            scrollState.scrollTo(current)
        }

        if (shouldMove) {
            shouldMove = false
            val currentPosition = scrollState.value
            if (currentSection > 0
                && currentPosition < sectionPositions[currentSection]
            ) {
                currentSection -= 1
            } else if (currentSection < sectionPositions.size - 1
                && currentPosition >=
                (sectionPositions[currentSection + 1] + sectionPositions[currentSection]) * 0.3
            ) {
                currentSection += 1
            }

            scope.launch {
                try {
                    scrollState.animateScrollTo(
                        sectionPositions[currentSection].toInt(),
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = EaseIn
                        )
                    )
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.initTopProfile()
        viewModel.initBottomProfile()
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = 50.dp)
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        when (event.type) {
                            PointerEventType.Release -> {
                                shouldMove = true
                            }
                        }
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier
                .height(sectionHeight.toDp.dp)
        ) {
            when (topProfileState) {
                is TopProfileUiState.Success -> {
                    val entity = (topProfileState as TopProfileUiState.Success).profileEntity
                    ProfileWidget(
                        navController = navController,
                        profileEntity = entity,
                        scrollState = scrollState
                    )
                }

                is TopProfileUiState.Error -> {

                }

                is TopProfileUiState.Init -> {

                }

                is TopProfileUiState.Loading -> {

                }
            }
        }

        when (bottomProfileState) {
            is BottomProfileUiState.Success -> {
                val entity =
                    (bottomProfileState as BottomProfileUiState.Success).bottomProfileEntity
                val educationEntity = entity.first
                val techEntity = entity.second
                Box(
                    modifier = Modifier
                        .height(sectionHeight.toDp.dp)
                        .background(Color.Green)
                ) {
                    EducationWidget(
                        educationEntity = educationEntity,
                        scrollState = scrollState
                    )
                }

                Box(
                    modifier = Modifier
                        .height(sectionHeight.toDp.dp)
                        .background(Color.Blue)
                ) {
                    EducationWidget(
                        educationEntity = educationEntity,
                        scrollState = scrollState
                    )
                }
            }

            is BottomProfileUiState.Error -> {

            }

            is BottomProfileUiState.Init -> {

            }

            is BottomProfileUiState.Loading -> {

            }
        }
    }
}
