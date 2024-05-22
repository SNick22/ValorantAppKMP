package com.example.valorantapp.features.agents.domain.entity

data class AgentEntity(
    val agentUuid: String,
    val name: String,
    val portrait: String,
    val roleName: String?,
    val roleIcon: String?
)
