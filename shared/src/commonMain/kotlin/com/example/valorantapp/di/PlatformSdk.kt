package com.example.valorantapp.di

import com.example.valorantapp.core.configuration.Configuration
import com.example.valorantapp.core.network.networkModule
import com.example.valorantapp.features.agents.agentsModule
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.LazyDelegate
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.instance
import org.kodein.di.singleton

object PlatformSdk {

    private var _di: DirectDI? = null

    val di: DirectDI
        get() = requireNotNull(_di)

    fun init(conf: Configuration) {
        _di = DI {
            importAll(
                createConfigurationModule(conf),
                networkModule,
                agentsModule
            )
        }.direct
    }

    inline fun <reified T : Any> lazyInstance(tag: Any? = null): LazyDelegate<T> {
        return di.lazy.instance(tag)
    }

    private fun createConfigurationModule(configuration: Configuration): DI.Module = DI.Module(
        name = "configurationModule",
        init = {
            bind<Configuration>() with singleton {
                configuration
            }
        },
    )
}
