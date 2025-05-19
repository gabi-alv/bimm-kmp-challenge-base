package com.bimm.takehomeassignmnent.sakes.data

import com.bimm.takehomeassignmnent.data.ResourceDataSource
import com.bimm.takehomeassignmnent.data.model.SakeShopModel

class LocalResourceSakesRepository(
    private val resourceDataSource: ResourceDataSource
): SakesRepository {
    var sakesList: List<SakeShopModel>? = null

    override suspend fun getSakeShops(): Result<List<SakeShopModel>> {
        return try {
            Result.success(sakesList ?: fetchSakesList())
        } catch (e: Throwable){
            Result.failure(e)
        }
    }

    override suspend fun getSakeShop(id: Int): Result<SakeShopModel> {
        return try {
            val list = sakesList ?: fetchSakesList()
            Result.success(list[id])
        } catch (e: Throwable){
            Result.failure(e)
        }
    }

    private suspend fun fetchSakesList(): List<SakeShopModel> {
        return resourceDataSource.resourceAsJson<List<SakeShopModel>>("files/sakeshop.json").also {
            sakesList = it
        }
    }
}