package com.example.valorantapp.features.agents.presentation

import com.example.valorantapp.features.agents.presentation.model.AgentModel

data class AgentsState(
    val isLoading: Boolean = false,
    val agents: List<AgentModel> = listOf()
)
