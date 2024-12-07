package com.keykat.presentation.screen.career

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keykat.domain.career.entity.CareerEntity
import com.keykat.presentation.careerViewModel

@Composable
fun CareerCompanySectionWidget(
    careerEntity: CareerEntity
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = careerEntity.name.toString(),
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = careerEntity.duration.toString(),
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = careerEntity.teamName.toString(),
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(12.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = careerEntity.position.toString(),
                style = MaterialTheme.typography.labelLarge
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(5.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = careerEntity.role.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}