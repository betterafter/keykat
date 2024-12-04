package com.keykat.presentation.screen.profile.widget.tech

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.TechEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechWidget(
    techEntity: List<TechEntity>,
    currentIndex: Int,
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        pageCount = { techEntity.size },
        initialPage = techEntity.size / 2,
        initialPageOffsetFraction = 0.1f
    )

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Tech Stack",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp, start = 20.dp)
        )

        Box(
            modifier = Modifier
                .weight(8f)
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        ) {
            TechContentWidget(
                techEntity = techEntity,
                scrollState = scrollState,
                pagerState = pagerState
            )
        }

        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .weight(2f)
        ) {
            TechControllerWidget(
                techEntity = techEntity,
                scrollState = scrollState,
                pagerState = pagerState
            )
        }
    }
}