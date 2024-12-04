package com.keykat.presentation.screen.profile.widget.tech

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.TechEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechControllerWidget(
    techEntity: List<TechEntity>,
    scrollState: ScrollState,
    pagerState: PagerState
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