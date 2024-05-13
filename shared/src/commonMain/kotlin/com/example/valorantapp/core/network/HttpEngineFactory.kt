package com.example.valorantapp.core.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class HttpEngineFactory() {

    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}
