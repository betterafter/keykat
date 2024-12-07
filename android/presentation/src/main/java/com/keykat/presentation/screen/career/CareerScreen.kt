package com.keykat.presentation.screen.career

import android.content.res.Resources
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keykat.presentation.screen.common.toDp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CareerScreen(
    navController: NavController,
    careerViewModel: CareerViewModel = com.keykat.presentation.careerViewModel()
) {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    val deviceHeight = Resources.getSystem().displayMetrics.heightPixels

    val careerUiState = careerViewModel.career.collectAsState()
    val mainColor = MaterialTheme.colorScheme.primary

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        careerViewModel.getCareer()
    }

    when (careerUiState.value) {
        is CareerUiState.Init -> {

        }

        is CareerUiState.Loading -> {

        }

        is CareerUiState.Success -> {
            val careerEntityList = (careerUiState.value as CareerUiState.Success).careerEntity
            val pagerState = rememberPagerState(pageCount = { careerEntityList.size })

            HorizontalPager(state = pagerState) { page ->
                val career = careerEntityList[page]
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        Box(contentAlignment = Alignment.Center) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .background(mainColor)
                            )

                            Box(
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                                    .background(mainColor, shape = CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box {
                            CareerCompanySectionWidget(
                                careerEntity = career
                            )
                        }

                        Box(modifier = Modifier.width(deviceWidth.toDp.dp / 2)) {
                            CareerSummarySectionWidget()
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter
            ) {
                CustomBottomModalSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.surfaceContainer,
                            shape = RoundedCornerShape(
                                topStart = 30.dp,
                                topEnd = 30.dp
                            )
                        )
                        .align(Alignment.BottomCenter)
                )
            }
        }

        is CareerUiState.Error -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomModalSheet(modifier: Modifier) {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    val deviceHeight = Resources.getSystem().displayMetrics.heightPixels

    val maxModalHeight = (deviceHeight / 2).toFloat()
    val minModalHeight = 100f
    var expanded by remember { mutableStateOf(true) }
    val modalHeight by animateDpAsState(
        targetValue = (if (expanded) maxModalHeight else minModalHeight).toDp.dp,
        label = ""
    )

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .padding(top = 20.dp)
            .height(modalHeight)
            .clickable {
                expanded = !expanded
            }
            .animateContentSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width((deviceWidth / 7).toDp.dp)
                    .height(6.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "안녕\n안녕\n안녕\n안녕\n" +
                        "안녕\n" +
                        "안녕\n" +
                        "안녕\n" +
                        "안녕\n" +
                        "안녕\n" +
                        "안녕"
            )
        }
    }
}