package com.bimm.takehomeassignmnent

interface Platform {
    val name: String
    val type: Type

    enum class Type {
        ANDROID, IOS
    }
}

expect fun getPlatform(): Platform
