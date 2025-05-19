package com.bimm.takehomeassignmnent.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import kotlinx.serialization.json.Json


abstract class NetworkDataSource(
    protected val baseUrl: String,
    protected val httpClient: HttpClient,
) {
    @Throws(Exception::class)
    protected suspend inline fun <reified T> get(path: String): T {
        return httpClient.get {
            url { "$baseUrl$path" }
        }.body()
    }

    @Throws(Exception::class)
    protected suspend inline fun <reified T, reified R> post(path: String, body: R): T {
        return httpClient.post {
            url { "$baseUrl$path" }
            setBody(body)
        }.body()
    }
}