package com.example.valorantapp.features.agents

import com.example.valorantapp.features.agents.data.repository.AgentRepositoryImpl
import com.example.valorantapp.features.agents.data.source.AgentRemoteSource
import com.example.valorantapp.features.agents.domain.repository.AgentRepository
import com.example.valorantapp.features.agents.domain.usecase.GetAgentsUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val agentsModule = DI.Module("agentsModule") {
    bindProvider {
        AgentRemoteSource(client = instance())
    }

    bindProvider<AgentRepository> {
        AgentRepositoryImpl(remoteSource = instance())
    }

    bindProvider {
        GetAgentsUseCase(repository = instance())
    }
}
