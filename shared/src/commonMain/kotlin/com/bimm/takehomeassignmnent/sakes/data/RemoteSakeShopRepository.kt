package com.bimm.takehomeassignmnent.sakes.data

import com.bimm.takehomeassignmnent.data.SakeShopApi
import com.bimm.takehomeassignmnent.data.model.SakeShopModel

/**
 *  We could use Room (now  officially supported on KMP!) to cache calls and reduce load speeds.
 *  Returning Result here makes our VMs code more clean (or our domain layers cleaner).
 */
class RemoteSakeShopRepository(
    private val sakeShopApi: SakeShopApi
): SakesRepository {
    override suspend fun getSakeShops(): Result<List<SakeShopModel>> {
        return try {
            Result.success(sakeShopApi.getSakeShops())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun getSakeShop(id: Int): Result<SakeShopModel> {
       return try {
           Result.success(sakeShopApi.getSakeShopDetails(id))
       } catch (e: Throwable){
           Result.failure(e)
       }
    }
}