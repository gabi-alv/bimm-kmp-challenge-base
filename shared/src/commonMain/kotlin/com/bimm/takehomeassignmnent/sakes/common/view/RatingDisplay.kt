package com.bimm.takehomeassignmnent.sakes.common.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmptakehomeassignment.shared.generated.resources.Res
import kmptakehomeassignment.shared.generated.resources.ic_star
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun RatingDisplay(
    modifier: Modifier = Modifier,
    rating: Double,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(iconSize).padding(end = 4.dp),
            painter = painterResource(Res.drawable.ic_star),
            tint = color,
            contentDescription = null
        )
        Text(
            text = formatDecimal(rating),
            style = textStyle,
            color = color
        )
    }
}

fun formatDecimal(value: Double): String {
    return ((value * 10).roundToInt() / 10.0).toString().replace(',', '.')
}