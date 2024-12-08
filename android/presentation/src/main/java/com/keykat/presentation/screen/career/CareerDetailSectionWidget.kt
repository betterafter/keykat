package com.keykat.presentation.screen.career

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keykat.domain.career.entity.CareerDetailEntity
import com.keykat.presentation.careerViewModel

@Composable
fun CareerDetailSectionWidget(
    careerDetailEntity: CareerDetailEntity?,
    mainColor: Color,
) {
    if (careerDetailEntity == null) {
        return
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        TitleWidget("소개", mainColor)
        ContentWidget(careerDetailEntity.introduce.toString())

        TitleWidget("업무 내용", mainColor)
        ContentWidget(careerDetailEntity.description.toString())
        
        Spacer(modifier = Modifier.height(40.dp))

        TitleWidget("기술 스택", mainColor)
        TechStackWidget(careerDetailEntity.techStack ?: listOf(), mainColor)
    }
}

@Composable
fun TitleWidget(
    type: String,
    mainColor: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .width(3.dp)
            .height(16.dp)
            .background(mainColor))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = type, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun ContentWidget(
    content: String
) {
    Text(
        content,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 20.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TechStackWidget(
    techStack: List<String>,
    mainColor: Color
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        FlowRow {
            techStack.forEach {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = mainColor.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(100)
                            )
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}