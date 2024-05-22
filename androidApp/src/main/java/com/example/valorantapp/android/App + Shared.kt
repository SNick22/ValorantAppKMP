package com.example.valorantapp.android

import com.example.valorantapp.core.configuration.Configuration
import com.example.valorantapp.di.PlatformSdk

fun App.initShared() {
    val configuration = Configuration(
        isDebug = BuildConfig.DEBUG,
        isLoggingEnabled = BuildConfig.DEBUG
    )
    PlatformSdk.init(configuration)
}
