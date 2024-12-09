package com.keykat.presentation.screen.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.keykat.domain.potfolio.entity.PortfolioCareerEntity

@Composable
fun PortfolioCareerWidget(
    navController: NavController,
    portfolioCareerEntity: List<PortfolioCareerEntity>
) {
    Column {
        portfolioCareerEntity.forEach {
            Card {
                AsyncImage(
                    model = it.thumbnail,
                    contentDescription = it.thumbnail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
        }
    }
}