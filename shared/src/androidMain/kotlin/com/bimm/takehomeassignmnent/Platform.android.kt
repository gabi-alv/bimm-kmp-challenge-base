package com.bimm.takehomeassignmnent

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override val type: Platform.Type = Platform.Type.ANDROID
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}