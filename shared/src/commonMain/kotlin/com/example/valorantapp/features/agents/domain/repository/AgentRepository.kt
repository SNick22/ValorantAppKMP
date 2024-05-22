package com.example.valorantapp.features.agents.domain.repository

import com.example.valorantapp.features.agents.domain.entity.AgentEntity

interface AgentRepository {

    suspend fun getAgents(): List<AgentEntity>
}
