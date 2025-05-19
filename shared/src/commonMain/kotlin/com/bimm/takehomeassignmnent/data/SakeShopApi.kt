package com.bimm.takehomeassignmnent.data

import com.bimm.takehomeassignmnent.data.model.SakeShopModel
import io.ktor.client.HttpClient

/**
 *  This is a dummy class representing a working alternative to swap the local json for an actual
 *  endpoint
 */
class SakeShopApi(
    httpClient: HttpClient
): NetworkDataSource(baseUrl = "https://example.com", httpClient = httpClient) {
    @Throws(Exception::class)
    suspend fun getSakeShops(): List<SakeShopModel> {
        return get("/shops")
    }
    @Throws(Exception::class)
    suspend fun getSakeShopDetails(id: Int): SakeShopModel {
        return get("/shops/$id")
    }
}