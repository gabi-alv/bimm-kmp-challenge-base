package com.bimm.takehomeassignmnent.sakes.list.model

sealed interface SakesListIntent {
    data object InitScreenIntent: SakesListIntent
    data class ClickSakeShopIntent(val id: Int): SakesListIntent
    data class OpenSakeshopWebsiteIntent(val url: String): SakesListIntent
}