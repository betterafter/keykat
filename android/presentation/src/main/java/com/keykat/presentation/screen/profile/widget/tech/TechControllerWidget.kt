package com.keykat.presentation.screen.profile.widget.tech

import android.content.res.Resources
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.TechEntity
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechControllerWidget(
    techEntity: List<TechEntity>,
    scrollState: ScrollState,
    pagerState: PagerState
) {
    val arcColor = MaterialTheme.colorScheme.background

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
            .background(MaterialTheme.colorScheme.primary)
                .padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.Bottom,
            contentPadding = PaddingValues(
                horizontal = 100.dp
            ),
            pageSpacing = 0.dp,
        ) { page ->
            val tech = techEntity[page]
            TechItem(
                pagerState = pagerState,
                currentIndex = page,
                tech = tech
            )
        }

        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            drawArc(
                color = arcColor,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechItem(
    pagerState: PagerState,
    currentIndex: Int,
    tech: TechEntity
) {
    var iconSize by remember { mutableIntStateOf(80) }

    LaunchedEffect(pagerState.getOffsetFractionForPage(currentIndex)) {
        snapshotFlow { pagerState.currentPage }.collect {
            iconSize = (80 - 20 * abs(pagerState.getOffsetFractionForPage(currentIndex))).toInt()
        }
    }
    Box() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(15)
                )
        ) {
            AsyncImage(
                model = tech.icon,
                contentDescription = tech.name,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(iconSize.dp)
                    .padding(10.dp)
            )
        }
    }
}