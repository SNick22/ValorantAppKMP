package com.example.valorantapp.features.agents.data.repository

import com.example.valorantapp.features.agents.data.mappers.toAgentEntity
import com.example.valorantapp.features.agents.data.source.AgentRemoteSource
import com.example.valorantapp.features.agents.domain.entity.AgentEntity
import com.example.valorantapp.features.agents.domain.repository.AgentRepository

class AgentRepositoryImpl(
    private val remoteSource: AgentRemoteSource
): AgentRepository {

    override suspend fun getAgents(): List<AgentEntity> {
        return remoteSource.getAgents().data
            .map { it.toAgentEntity() }
    }
}
