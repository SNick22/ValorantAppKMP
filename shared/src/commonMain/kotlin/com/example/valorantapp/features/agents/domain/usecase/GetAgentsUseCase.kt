package com.example.valorantapp.features.agents.domain.usecase

import com.example.valorantapp.features.agents.domain.entity.AgentEntity
import com.example.valorantapp.features.agents.domain.repository.AgentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class GetAgentsUseCase(
    private val repository: AgentRepository
) {

    suspend operator fun invoke(): List<AgentEntity> =
        withContext(Dispatchers.IO) {
            repository.getAgents()
        }
}
