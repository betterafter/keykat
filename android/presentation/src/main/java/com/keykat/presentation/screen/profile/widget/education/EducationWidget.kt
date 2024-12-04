package com.keykat.presentation.screen.profile.widget.education

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.keykat.domain.profile.entity.EducationEntity
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.ProfileViewModel


@Composable
fun EducationWidget(
    educationEntity: List<EducationEntity>,
    viewModel: ProfileViewModel = profileViewModel()
) {
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "EDUCATION",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(30.dp))
        educationEntity.map {
            Text(
                text = it.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it.where,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(12.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = it.duration,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it.content,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(42.dp))
        }
    }
}