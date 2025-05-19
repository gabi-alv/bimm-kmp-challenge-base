package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.data.ResourceDataSource
import com.bimm.takehomeassignmnent.data.SakeShopApi
import com.bimm.takehomeassignmnent.sakes.di.sakesModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kmptakehomeassignment.shared.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.InternalResourceApi
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import org.jetbrains.compose.resources.readResourceBytes
import org.koin.core.module.Module

object DependencyGraph {
    lateinit var koin: Koin
        private set

    fun initModules(vararg mdls: Module) {
        koin = startKoin{
            modules(mdls.toList())
        }.koin
    }
}


fun buildDependencies() {
   DependencyGraph.initModules(
       appModule(),
       sakesModule()
   )
}



@OptIn(InternalResourceApi::class)
fun appModule() = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
    single<ResourceDataSource> {
        ResourceDataSource(
            json = get(),
            resourceStringReader = { path ->
                Res.readBytes(path).decodeToString()
            }
        )
    }
    single<HttpClient> {
        val json: Json = get()
        HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level  = LogLevel.BODY
            }
            install(ContentNegotiation) {
                json(json)
            }
        }
    }
    single<SakeShopApi> {
        SakeShopApi(get())
    }
}