import org.jetbrains.kotlin.com.google.common.base.Functions.compose

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply  false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.compose.compiler) apply  false
    id("org.jetbrains.kotlin.native.cocoapods") version "1.9.0" apply false
   // kotlin("plugin.compose") version "2.2.0-RC" apply false

}