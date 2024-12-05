package com.keykat.presentation.screen.profile.widget.tech

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.TechEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechWidget(
    techEntity: List<TechEntity>,
) {
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
                text = "TECH STACK",
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