package com.keykat.presentation.screen.career

import android.content.res.Resources
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowDown
import androidx.compose.material.icons.rounded.KeyboardDoubleArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keykat.presentation.careerViewModel
import com.keykat.presentation.screen.common.toDp
import com.keykat.presentation.screen.common.toPx

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CareerScreen(
    navController: NavController,
    careerViewModel: CareerViewModel = com.keykat.presentation.careerViewModel()
) {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
    val deviceHeight = Resources.getSystem().displayMetrics.heightPixels

    val careerUiState = careerViewModel.career.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }

    val mainColorList = listOf(
        MaterialTheme.colorScheme.primary,
        Color.Green
    )

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
                val mainColor = Color(career.mainColor)

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
                                    .background(color = mainColor)
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
                                careerEntity = career,
                                mainColor = mainColor
                            )
                        }

                        Spacer(modifier = Modifier.height(60.dp))

                        Row {
                            Box(
                                modifier = Modifier
                                    .width(10.dp)
                                    .fillMaxHeight()
                                    .background(
                                        color = mainColor,
                                        shape = RoundedCornerShape(
                                            topEnd = 10.dp,
                                            topStart = 10.dp
                                        )
                                    )
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                career.careerDetail.forEach { detail ->
                                    Box {
                                        CareerSummarySectionWidget(
                                            careerEntity = career,
                                            careerDetailEntity = detail,
                                            mainColor = mainColor
                                        )
                                    }
                                }
                            }
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

@Composable
fun CustomBottomModalSheet(
    modifier: Modifier,
    viewModel: CareerViewModel = careerViewModel(),
) {
    val deviceHeight = Resources.getSystem().displayMetrics.heightPixels

    val maxModalHeight = (deviceHeight / 1.8).toFloat()
    val minModalHeight = (20.dp + 40.dp).toPx.value
    val expanded = viewModel.isExpandedModalBottomSheet.collectAsState().value
    val modalHeight by animateDpAsState(
        targetValue = (if (expanded) maxModalHeight else minModalHeight).toDp.dp,
        label = ""
    )

    val career = viewModel.currentClickedCareer.collectAsState()
    val careerDetail = viewModel.currentClickedCareerDetail.collectAsState()
    val mainColor = if (career.value != null) {
        Color(career.value!!.mainColor)
    } else {
        MaterialTheme.colorScheme.primary
    }


    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .height(modalHeight)
            .animateContentSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {
                        if (expanded) {
                            viewModel.closeModalBottomSheet()
                        } else {
                            viewModel.openModalBottomSheet()
                        }
                    }
            ) {
                if (expanded) {
                    Icon(
                        Icons.Rounded.KeyboardDoubleArrowDown,
                        contentDescription = "내리기",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                } else {
                    Icon(
                        Icons.Rounded.KeyboardDoubleArrowUp,
                        contentDescription = "올리기",
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            CareerDetailSectionWidget(
                mainColor = mainColor,
                careerDetailEntity = careerDetail.value
            )
        }
    }
}