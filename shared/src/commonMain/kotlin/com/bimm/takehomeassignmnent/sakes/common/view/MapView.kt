package com.bimm.takehomeassignmnent.sakes.common.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MapView(
    latitude: Double,
    longitude: Double,
    zoom: Float = 12f,
    modifier: Modifier = Modifier
) {
    PlatformMapView(
        latitude = latitude,
        longitude = longitude,
        zoom = zoom,
        modifier = modifier
    )
}

@Composable
expect fun PlatformMapView(
    modifier: Modifier,
    latitude: Double,
    longitude: Double,
    zoom: Float,
)