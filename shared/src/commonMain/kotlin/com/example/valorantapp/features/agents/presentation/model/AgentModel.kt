package com.example.valorantapp.features.agents.presentation.model

data class AgentModel(
    val agentUuid: String,
    val name: String,
    val portrait: String,
    val roleName: String?,
    val roleIcon: String?
)
