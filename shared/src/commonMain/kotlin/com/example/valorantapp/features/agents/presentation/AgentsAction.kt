package com.example.valorantapp.features.agents.presentation

sealed class AgentsAction {
    data class NavigateToAgentDetails(val uuid: String): AgentsAction()
}
