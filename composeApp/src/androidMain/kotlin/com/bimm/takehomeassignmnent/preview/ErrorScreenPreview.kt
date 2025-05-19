package com.bimm.takehomeassignmnent.preview

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bimm.takehomeassignmnent.common.view.ErrorScreen

@Preview
@Composable
fun ErrorScreenPreview(){
    MaterialTheme {
        ErrorScreen(
            onRetryClick = {}
        )
    }
}
