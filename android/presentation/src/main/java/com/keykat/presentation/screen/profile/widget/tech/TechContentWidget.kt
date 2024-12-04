package com.keykat.presentation.screen.profile.widget.tech

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.TechEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechContentWidget(
    techEntity: List<TechEntity>,
    scrollState: ScrollState,
    pagerState: PagerState
) {
    var tech by remember { mutableStateOf(techEntity[pagerState.currentPage]) }

    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            tech = techEntity[page]
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = tech.name
        )

        Text(text = tech.content.toString())

    }
}