package com.keykat.presentation.screen.profile.widget.tech

import android.content.res.Resources
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.TechEntity
import com.keykat.presentation.screen.common.toDp
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TechControllerWidget(
    techEntity: List<TechEntity>,
    pagerState: PagerState
) {
    Box(
        contentAlignment = Alignment.TopCenter,
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
                .padding(top = 20.dp, bottom = 10.dp),
            verticalAlignment = Alignment.Bottom,
            contentPadding = PaddingValues(
                horizontal = 120.dp
            ),
            pageSpacing = 30.dp,
        ) { page ->
            val tech = techEntity[page]
            TechItem(
                pagerState = pagerState,
                currentIndex = page,
                tech = tech
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
    val itemWidth = Resources.getSystem().displayMetrics.widthPixels / 3
    var cardRotation by remember { mutableFloatStateOf(0f) }
    var cardBottomPadding by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(pagerState.getOffsetFractionForPage(currentIndex)) {
        snapshotFlow { pagerState.currentPage }.collect {
            cardRotation =
                20 * pagerState.getOffsetFractionForPage(currentIndex)
            cardBottomPadding = 40 * abs(pagerState.getOffsetFractionForPage(currentIndex))
        }
    }
    Card(
        modifier = Modifier
            .fillMaxHeight()
            .width(itemWidth.toDp.dp)
            .padding(bottom = 20.dp + cardBottomPadding.dp)
            .graphicsLayer {
                rotationZ = cardRotation
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .height(150.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(15)
                    )

            ) {
                AsyncImage(
                    model = tech.icon,
                    contentDescription = tech.name,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(80.dp)
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = tech.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}