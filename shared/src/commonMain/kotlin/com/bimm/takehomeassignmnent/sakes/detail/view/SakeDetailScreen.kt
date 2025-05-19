package com.bimm.takehomeassignmnent.sakes.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bimm.takehomeassignmnent.arch.Event
import com.bimm.takehomeassignmnent.arch.view.AppScaffolding
import com.bimm.takehomeassignmnent.common.view.ErrorScreen
import com.bimm.takehomeassignmnent.common.view.LoadingScreen
import com.bimm.takehomeassignmnent.data.model.SakeShopModel
import com.bimm.takehomeassignmnent.sakes.common.view.MapView
import com.bimm.takehomeassignmnent.sakes.common.view.RatingDisplay
import com.bimm.takehomeassignmnent.sakes.detail.model.SakesDetailIntent
import com.bimm.takehomeassignmnent.sakes.detail.model.SakesDetailState
import com.bimm.takehomeassignmnent.sakes.list.model.SakesListIntent
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kmptakehomeassignment.shared.generated.resources.Res
import kmptakehomeassignment.shared.generated.resources.app_name
import kmptakehomeassignment.shared.generated.resources.go_back
import kmptakehomeassignment.shared.generated.resources.open_in_maps_button
import kmptakehomeassignment.shared.generated.resources.sake_details_about_title
import kmptakehomeassignment.shared.generated.resources.sake_details_address_title
import kmptakehomeassignment.shared.generated.resources.shop_website
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource

@Composable
fun SakeDetailScreen(
    state: StateFlow<SakesDetailState>,
    events: SharedFlow<Event>,
    onIntent: (SakesDetailIntent) -> Unit
){
    val uiState by state.collectAsState()
    AppScaffolding(
        title = uiState.data?.name.orEmpty(),
        onBackNavigation = {
            onIntent(SakesDetailIntent.NavigateBackIntent)
        },
        events = events
    ){
        when {
            uiState.isLoading -> LoadingScreen()
            uiState.loadingError != null || uiState.data == null  -> ErrorScreen(
                onRetryClick = {
                    onIntent(SakesDetailIntent.InitializeScreenIntent)
                }
            )
            else -> Content(uiState.data!!, onIntent)
        }
    }
    LaunchedEffect(Unit){
        onIntent(SakesDetailIntent.InitializeScreenIntent)
    }
}

@Composable
private fun Content(data: SakeShopModel, onIntent: (SakesDetailIntent) -> Unit) {
    Column(
        Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        data.picture?.let {
            KamelImage(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                resource = asyncPainterResource(data = it),
                contentScale = ContentScale.Crop,
                onLoading = {
                    CircularProgressIndicator()
                },
                contentDescription = null
            )
        } ?: run {
            Spacer(Modifier.height(24.dp))
        }

        SectionCard {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(Res.string.sake_details_about_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            RatingDisplay(
                iconSize = 24.dp,
                textStyle = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp),
                rating = data.rating
            )
            Text(
                text = data.description
            )
            TextButton(
                onClick = { onIntent(SakesDetailIntent.OpenExternalWebsiteIntent(data.website)) }
            ){
                Text(
                    text = stringResource(Res.string.shop_website),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        SectionCard {
            Text(
                text = stringResource(Res.string.sake_details_address_title),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = data.address,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            data.coordinates.takeIf { it.size == 2 }?.let {
                MapView(
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp),
                    latitude = it[0],
                    longitude = it[1]
                )
            }
            TextButton(
                onClick = { onIntent(SakesDetailIntent.OpenGoogleMapsIntent(data.googleMapsLink)) },
                modifier = Modifier.padding(16.dp)
            ){
                Text(
                    text = stringResource(Res.string.open_in_maps_button),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.large
                ),
            onClick = { onIntent(SakesDetailIntent.NavigateBackIntent) }
        ){
          Text(
              text = stringResource(Res.string.go_back),
              style = MaterialTheme.typography.titleLarge,
              color = MaterialTheme.colorScheme.onPrimary
          )
        }
    }
}

@Composable
private fun SectionCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.large
        )
        .padding(16.dp)
    ) {
        content()
    }
}