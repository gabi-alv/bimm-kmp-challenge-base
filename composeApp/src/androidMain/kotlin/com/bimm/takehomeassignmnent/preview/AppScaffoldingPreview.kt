package com.bimm.takehomeassignmnent.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bimm.takehomeassignmnent.arch.view.AppScaffolding
import kotlinx.coroutines.flow.MutableSharedFlow

@Preview
@Composable
fun AppScaffoldingPreview() {
    MaterialTheme {
        AppScaffolding(
            title = "SakesDemo",
            events = MutableSharedFlow(),
            onBackNavigation = {}
        ){
            Box(Modifier.fillMaxSize()){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Demo"
                )
            }
        }
    }
}