package com.bimm.takehomeassignmnent

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

object KmpRuntimeContext{
    lateinit var androidContext: WeakReference<Application>
        private set

    fun initKmpContext(context: Application) {
        androidContext = WeakReference(context)
    }
}