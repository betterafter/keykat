package com.keykat.presentation.screen.profile.widget.tech

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.TechEntity

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun TechContentWidget(
    techEntity: List<TechEntity>,
    pagerState: PagerState
) {
    val arcColor = MaterialTheme.colorScheme.background
    var tech by remember { mutableStateOf(techEntity[pagerState.currentPage]) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            tech = techEntity[page]
        }
    }

    Column {
        Text(
            text = tech.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Column(
            modifier = Modifier
                .height(270.dp)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
        ) {
            Text(text = tech.content.toString(), style = MaterialTheme.typography.bodyMedium)
        }

        Box {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(color = arcColor)
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


            Box(modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
            ) {
                FlowRow {
                    tech.stacks?.forEach {
                        Box(modifier = Modifier
                            .padding(2.dp)) {
                            Box(modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.inversePrimary,
                                    shape = RoundedCornerShape(100)
                                )
                                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                            ) {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}