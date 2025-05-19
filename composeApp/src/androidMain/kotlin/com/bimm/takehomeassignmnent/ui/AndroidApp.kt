package com.bimm.takehomeassignmnent.ui

import android.app.Application
import com.bimm.takehomeassignmnent.KmpRuntimeContext
import com.bimm.takehomeassignmnent.di.buildDependencies

class AndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        KmpRuntimeContext.initKmpContext(this)
        buildDependencies()
    }
}