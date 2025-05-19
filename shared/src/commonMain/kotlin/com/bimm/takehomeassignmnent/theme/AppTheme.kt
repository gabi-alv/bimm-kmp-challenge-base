package com.bimm.takehomeassignmnent.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColors = lightColorScheme(
    primary        = Color(0xFF6200EE),
    onPrimary      = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFBB86FC),
    onPrimaryContainer = Color(0xFF3700B3),
    secondary      = Color(0xFF03DAC6),
    onSecondary    = Color(0xFF000000),
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color(0xFFFFFFFF),
    background     = Color(0xFFFFFFFF),
    onBackground   = Color(0xFF000000),
    surface        = Color(0xFFFFFFFF),
    onSurface      = Color(0xFF000000),
    error          = Color(0xFFB00020),
    onError        = Color(0xFFFFFFFF),
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
