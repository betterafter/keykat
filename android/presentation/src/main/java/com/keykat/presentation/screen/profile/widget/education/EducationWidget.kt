package com.keykat.presentation.screen.profile.widget.education

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.keykat.domain.profile.entity.EducationEntity


@Composable
fun EducationWidget(
    educationEntity: List<EducationEntity>,
    scrollState: ScrollState
) {
    Column {
        educationEntity.map { 
            Row {
                Text(text = "이름")
                Text(text = "기관")
            }
            Text(text = "날짜")
            Text(text = "설명")
        }
    }
}