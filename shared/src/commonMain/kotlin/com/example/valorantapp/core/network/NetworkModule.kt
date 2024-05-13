package com.example.valorantapp.core.network

import com.example.valorantapp.core.configuration.Configuration
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.AttributeKey
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

val networkModule = DI.Module("networkModule") {

    bind<HttpEngineFactory>() with singleton { HttpEngineFactory() }

    bindSingleton<Json> {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    bindSingleton {
        buildHttpClient(
            engine = instance<HttpEngineFactory>().createEngine(),
            json = instance(),
            configuration = instance(),
        )
    }
}

private const val BASE_URL = "valorant-api.com/v1"

private fun buildHttpClient(
    engine: HttpClientEngineFactory<HttpClientEngineConfig>,
    json: Json,
    configuration: Configuration
) = HttpClient(engine) {

    if (configuration.isLoggingEnabled) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
    }

    install(ContentNegotiation) {
        json(json)
    }

    install(HttpTimeout) {
        connectTimeoutMillis = 15000
        requestTimeoutMillis = 30000
        socketTimeoutMillis = 30000
    }
    defaultRequest {
        url {
            this.host = BASE_URL
            this.protocol = URLProtocol.HTTPS
        }
        setAttributes {
            put(
                key = AttributeKey("language"),
                value = "ru-RU"
            )
        }
    }
}
