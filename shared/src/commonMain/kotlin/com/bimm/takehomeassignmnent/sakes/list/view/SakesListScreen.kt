package com.bimm.takehomeassignmnent.sakes.list.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bimm.takehomeassignmnent.arch.Event
import com.bimm.takehomeassignmnent.arch.view.AppScaffolding
import com.bimm.takehomeassignmnent.common.view.ErrorScreen
import com.bimm.takehomeassignmnent.common.view.LoadingScreen
import com.bimm.takehomeassignmnent.sakes.common.view.RatingDisplay
import com.bimm.takehomeassignmnent.sakes.list.model.SakeShopListUiItem
import com.bimm.takehomeassignmnent.sakes.list.model.SakesListIntent
import com.bimm.takehomeassignmnent.sakes.list.model.SakesListState
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kmptakehomeassignment.shared.generated.resources.Res
import kmptakehomeassignment.shared.generated.resources.app_name
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource

@Composable
fun SakeListScreen(state: StateFlow<SakesListState>, events: SharedFlow<Event>, onIntent: (SakesListIntent) -> Unit) {
    AppScaffolding(
        title = stringResource(Res.string.app_name),
        events =  events
    ){
        val uiState by state.collectAsState()
        when {
            uiState.isLoading -> LoadingScreen()
            uiState.loadingError != null || uiState.data.isEmpty() -> ErrorScreen(
                onRetryClick = {
                    onIntent(SakesListIntent.InitScreenIntent)
                }
            )
            else -> Content(uiState, onIntent)
        }
        LaunchedEffect(Unit){
            onIntent(SakesListIntent.InitScreenIntent)
        }
    }
}

@Composable
private fun Content(uiState: SakesListState, onIntent: (SakesListIntent) -> Unit) {
    LazyColumn(
        Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        items(uiState.data){ item ->
            SakeListItem(item, onIntent)
        }
    }
}

@Composable
fun SakeListItem(item: SakeShopListUiItem, onIntent: (SakesListIntent) -> Unit){
    Row(
        Modifier.fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            .padding(16.dp)
            .clickable {
                onIntent(SakesListIntent.ClickSakeShopIntent(item.id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.picture?.let {
            KamelImage(
                modifier = Modifier.sizeIn(
                    maxHeight = 100.dp ,
                    maxWidth = 100.dp,
                    minWidth = 100.dp,
                    minHeight = 100.dp
                ).clip(CircleShape),
                contentScale = ContentScale.Crop,
                resource = asyncPainterResource(data = item.picture),
                onFailure = { error ->
                    error.printStackTrace()
                },
                onLoading = {
                    CircularProgressIndicator()
                },
                contentDescription = null
            )
        }
        Column(Modifier.padding(16.dp).weight(1f)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            RatingDisplay(
                rating = item.rating
            )
        }
    }
}