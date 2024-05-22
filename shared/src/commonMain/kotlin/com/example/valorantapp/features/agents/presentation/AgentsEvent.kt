package com.example.valorantapp.features.agents.presentation

import com.example.valorantapp.features.agents.presentation.model.AgentModel

sealed class AgentsEvent {
    data class OnAgentClick(val agent: AgentModel): AgentsEvent()
}
