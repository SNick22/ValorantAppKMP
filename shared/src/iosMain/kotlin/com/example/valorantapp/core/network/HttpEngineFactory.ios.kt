package com.example.valorantapp.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.config
import io.ktor.client.engine.darwin.Darwin

actual class HttpEngineFactory actual constructor() {

    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin.config {
        configureRequest {
//            this.
        }
    }
}