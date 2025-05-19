package com.bimm.takehomeassignmnent.sakes.list.model

data class SakesListState(
    val isLoading: Boolean = true,
    val data: List<SakeShopListUiItem> = emptyList(),
    val loadingError: Throwable? = null
)