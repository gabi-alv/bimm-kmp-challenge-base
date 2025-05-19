package com.bimm.takehomeassignmnent.arch

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import com.bimm.takehomeassignmnent.KmpRuntimeContext


actual fun openExternalUrl(url: String) {
   KmpRuntimeContext.androidContext.get()?.let { context: Context ->
       val intent = Intent(Intent.ACTION_VIEW, url.toUri())
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
       context.startActivity(intent)
   }
}