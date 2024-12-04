package com.keykat.presentation.screen.profile

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.common.navigationHeight
import com.keykat.presentation.screen.profile.widget.education.EducationWidget
import com.keykat.presentation.screen.profile.widget.profile.ProfileWidget

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("ReturnFromAwaitPointerEventScope")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = profileViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    profileViewModel().setScrollState(pagerState)
    val topProfileState by viewModel.topProfile.collectAsState()
    val bottomProfileState by viewModel.bottomProfile.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.initTopProfile()
        viewModel.initBottomProfile()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        VerticalPager(
            state = pagerState,
            beyondBoundsPageCount = 1,
            pageSpacing = 0.dp,
            contentPadding = PaddingValues(0.dp),
        ) { page ->
            viewModel.setCurrentPage(pagerState.currentPage)

            when(page) {
                0 -> TopProfileSection(
                    topProfileState = topProfileState,
                    navController = navController,
                    0,
                )

                1 -> EducationSection(
                    bottomProfileState = bottomProfileState,
                    navController = navController,
                    1,
                )

                2 -> TechSection(
                    bottomProfileState = bottomProfileState,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun TopProfileSection(
    topProfileState: TopProfileUiState,
    navController: NavController,
    currentIndex: Int
) {
    val pageHeight = Resources.getSystem().displayMetrics.heightPixels - navigationHeight
    Box(
        modifier = Modifier
            .height(pageHeight.dp)
    ) {
        when (topProfileState) {
            is TopProfileUiState.Success -> {
                val entity = topProfileState.profileEntity
                ProfileWidget(
                    navController = navController,
                    profileEntity = entity,
                    currentIndex = currentIndex
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
}

@Composable
fun EducationSection(
    bottomProfileState: BottomProfileUiState,
    navController: NavController,
    currentIndex: Int
) {
    val pageHeight = Resources.getSystem().displayMetrics.heightPixels - navigationHeight
    when (bottomProfileState) {
        is BottomProfileUiState.Success -> {
            val entity =
                bottomProfileState.bottomProfileEntity
            val educationEntity = entity.first
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                EducationWidget(
                    educationEntity = educationEntity,
                    currentIndex = currentIndex
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

@Composable
fun TechSection(
    bottomProfileState: BottomProfileUiState,
    navController: NavController
) {
    when (bottomProfileState) {
        is BottomProfileUiState.Success -> {
            val entity =
                bottomProfileState.bottomProfileEntity
            val educationEntity = entity.first
            val techEntity = entity.second
            Box(
                modifier = Modifier
                    .background(Color.Green)
            ) {
                EducationWidget(
                    educationEntity = educationEntity,
                    1
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