package com.bimm.takehomeassignmnent.sakes.common.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.*


@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformMapView(
    modifier: Modifier,
    latitude: Double,
    longitude: Double,
    zoom: Float,
) {
    val clLocation = remember(latitude, latitude) {
        CLLocationCoordinate2DMake(
            latitude,
            longitude
        )
    }
    UIKitView(
        factory = {
            MKMapView().apply {
                setZoomEnabled(true)
                setScrollEnabled(true)
            }
        },
        update = { mapView: MKMapView ->
            mapView.removeAnnotations(mapView.annotations)

            val pointAnnotation = MKPointAnnotation().apply {
                setCoordinate(clLocation)
            }
            mapView.addAnnotation(pointAnnotation)
            mapView.setCenterCoordinate(clLocation, animated = true)
        },
        modifier = modifier
    )
}