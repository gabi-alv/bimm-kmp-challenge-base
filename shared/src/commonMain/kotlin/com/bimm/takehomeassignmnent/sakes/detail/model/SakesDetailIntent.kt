package com.bimm.takehomeassignmnent.sakes.detail.model

sealed interface SakesDetailIntent {
    data object InitializeScreenIntent: SakesDetailIntent
    data class OpenGoogleMapsIntent(val googleUrl: String): SakesDetailIntent
    data class OpenExternalWebsiteIntent(val url: String): SakesDetailIntent
    data object NavigateBackIntent: SakesDetailIntent
}