package com.bimm.takehomeassignmnent.data

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.internal.readJson

/**
 *  Local resource data source to parse the json file from our composeResources files
 */
class ResourceDataSource(
    val json: Json,
    // injected so we can unit test this class.
    private val resourceStringReader: suspend (String) -> String
) {
    @Throws(Exception::class)
    suspend fun resourceAsString(resourceName: String): String  = resourceStringReader(resourceName)

    @Throws(Exception::class)
    suspend inline fun <reified T> resourceAsJson(resorceName: String): T {
        return json.decodeFromString(resourceAsString(resorceName))
    }
}