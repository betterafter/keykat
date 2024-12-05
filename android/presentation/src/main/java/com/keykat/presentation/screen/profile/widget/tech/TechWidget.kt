package com.keykat.presentation.screen.profile.widget.tech

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.TechEntity
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechWidget(
    techEntity: List<TechEntity>,
    currentIndex: Int,
) {
    val arcColor = MaterialTheme.colorScheme.background
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        pageCount = { techEntity.size },
        initialPage = techEntity.size / 2,
    )

    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        TechControllerWidget(
            techEntity = techEntity,
            scrollState = scrollState,
            pagerState = pagerState
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Tech Stack",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp)
            )

            Box(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                TechContentWidget(
                    techEntity = techEntity,
                    pagerState = pagerState
                )
            }
        }
    }
}