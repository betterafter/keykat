package com.keykat.presentation.screen.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.keykat.domain.potfolio.entity.PortfolioCareerEntity

@Composable
fun PortfolioCareerWidget(
    navController: NavController,
    portfolioCareerEntity: List<PortfolioCareerEntity>
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "CAREER",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)
        )
        portfolioCareerEntity.forEach {
            if (it.parent == null) {

                val color = if (it.background != null) {
                    Color(
                        android.graphics.Color.parseColor(
                            it.background
                        )
                    )
                } else {
                    Color.White
                }

                Card(
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = if (it.children.isEmpty()) {
                                30.dp
                            } else {
                                0.dp
                            }
                        )
                        .clickable {

                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 30.dp
                    )
                ) {
                    AsyncImage(
                        model = it.thumbnail,
                        contentDescription = it.thumbnail,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .background(
                                color = color
                            )
                            .padding(30.dp)
                            .height(100.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.onSecondary)
                    ) {
                        Text(
                            text = it.name.toString(),
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(top = 20.dp, start = 10.dp)
                        )

                        Text(
                            text = it.introduce.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        )

                        Text(
                            text = it.duration.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
                        )
                    }
                }

                if (it.children.isNotEmpty()) {
                    it.children.forEach { child ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(5.dp)
                                    .height(30.dp)
                                    .background(MaterialTheme.colorScheme.inversePrimary)
                            )
                            Card(
                                modifier = Modifier
                                    .padding(
                                        start = 20.dp,
                                        end = 20.dp,
                                        bottom = 30.dp
                                    )
                                    .fillMaxWidth()
                                    .height(100.dp)
                            ) {
                                Row {
                                    Box(
                                        modifier = Modifier
                                            .background(Color.White)
                                            .width(100.dp)
                                            .height(100.dp)
                                    ) {
                                        AsyncImage(
                                            model = child.thumbnail,
                                            contentDescription = child.thumbnail,
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .background(color = Color.White)
                                                .width(100.dp)
                                                .height(100.dp)

                                        )
                                    }

                                    Column(
                                        modifier = Modifier.padding(
                                            start = 10.dp,
                                            top = 20.dp,
                                            end = 10.dp
                                        )
                                    ) {
                                        Text(
                                            text = child.name.toString(),
                                            style = MaterialTheme.typography.titleLarge,
                                        )

                                        Text(
                                            text = child.introduce.toString(),
                                            style = MaterialTheme.typography.labelLarge,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}