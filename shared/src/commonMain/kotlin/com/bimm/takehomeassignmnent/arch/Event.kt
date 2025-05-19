package com.bimm.takehomeassignmnent.arch

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

/**
 *  This interface represents all the one-time events that our app executes within compose.
 */
sealed interface Event

data class NavigationEvent(
    val route: Route
): Event

data class OpenExternalBrowserEvent(
    val url: String
): Event

object NavigateBackEvent: Event

data class SnackBarEvent @OptIn(ExperimentalResourceApi::class) constructor(
    val messageKey: StringResource
): Event