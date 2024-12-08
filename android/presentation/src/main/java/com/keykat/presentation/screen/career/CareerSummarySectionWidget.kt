package com.keykat.presentation.screen.career

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.keykat.domain.career.entity.CareerDetailEntity
import com.keykat.domain.career.entity.CareerEntity
import com.keykat.presentation.careerViewModel
import com.keykat.presentation.screen.common.toDp

@Composable
fun CareerSummarySectionWidget(
    careerDetailEntity: CareerDetailEntity,
    mainColor: Color,
    viewModel: CareerViewModel = careerViewModel()
) {
    val deviceWidth = Resources.getSystem().displayMetrics.widthPixels

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 40.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            (0..2).forEach { _ ->
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(5.dp)
                        .background(
                            mainColor, shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.width(7.dp))
            }
        }

        Box(
            modifier = Modifier
                .width((deviceWidth * 0.8).toFloat().toDp.dp)
                .padding(end = 20.dp)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.White
                    )
                )
                .clickable { viewModel.clickDetail(careerDetailEntity) }
            ,
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = careerDetailEntity.name.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = careerDetailEntity.duration.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = mainColor
                )
            }
        }
    }
}