package com.bimm.takehomeassignmnent.sakes.detail.model

import com.bimm.takehomeassignmnent.data.model.SakeShopModel

data class SakesDetailState(
    val id: Int,
    val isLoading: Boolean = true,
    val data: SakeShopModel? = null,
    val loadingError: Throwable? = null
)