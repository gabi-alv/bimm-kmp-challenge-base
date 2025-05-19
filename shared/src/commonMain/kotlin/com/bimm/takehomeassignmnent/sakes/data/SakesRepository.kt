package com.bimm.takehomeassignmnent.sakes.data

import com.bimm.takehomeassignmnent.data.model.SakeShopModel


interface SakesRepository {
    suspend fun getSakeShops(): Result<List<SakeShopModel>>
    suspend fun getSakeShop(id: Int): Result<SakeShopModel>
}