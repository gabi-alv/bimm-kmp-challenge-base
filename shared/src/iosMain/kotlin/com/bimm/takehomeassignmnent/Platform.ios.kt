package com.bimm.takehomeassignmnent

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: Platform.Type = Platform.Type.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()