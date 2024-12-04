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
//            .padding(end = 20.dp)
    ) {
        Row(

        ) {
            Box(
                contentAlignment = Alignment.BottomStart,
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .horizontalScroll(scrollState)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(
                                topEnd = 160f
                            )
                        )
                        .padding(top = 20.dp, bottom = 10.dp)
                        .width(200.dp),
                    verticalAlignment = Alignment.Bottom,
                    contentPadding = PaddingValues(
                        start = 60.dp, end = 50.dp
                    ),
                    pageSpacing = 8.dp,
                ) { page ->
                    val tech = techEntity[page]
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(15)
                            )
                    ) {
                        AsyncImage(
                            model = tech.icon,
                            contentDescription = tech.name,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}