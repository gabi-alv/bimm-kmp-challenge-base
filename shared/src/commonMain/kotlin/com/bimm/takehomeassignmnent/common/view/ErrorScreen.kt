package com.bimm.takehomeassignmnent.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmptakehomeassignment.shared.generated.resources.Res
import kmptakehomeassignment.shared.generated.resources.error_screen_retry
import kmptakehomeassignment.shared.generated.resources.error_screen_subtitle
import kmptakehomeassignment.shared.generated.resources.error_screen_title
import org.jetbrains.compose.resources.stringResource


@Composable
fun ErrorScreen(
    onRetryClick: (() -> Unit)? = null
){
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(Modifier.padding(24.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.titleLarge,
                text = stringResource(Res.string.error_screen_title)
            )
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = stringResource(Res.string.error_screen_subtitle),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            onRetryClick?.let {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.large,
                        ),
                    onClick = it
                ){
                    Text(
                        text = stringResource(Res.string.error_screen_retry),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }

    }
}