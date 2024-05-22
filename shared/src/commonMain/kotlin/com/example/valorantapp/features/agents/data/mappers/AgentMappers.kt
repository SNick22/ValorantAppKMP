package com.example.valorantapp.features.agents.data.mappers

import com.example.valorantapp.features.agents.data.source.model.AgentResponse
import com.example.valorantapp.features.agents.domain.entity.AgentEntity

fun AgentResponse.toAgentEntity(): AgentEntity =
    AgentEntity(
        agentUuid = this.uuid,
        name = this.displayName,
        portrait = this.displayIcon,
        roleName = this.role?.displayName,
        roleIcon = this.role?.displayIcon
    )
