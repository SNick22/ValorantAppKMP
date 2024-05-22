package com.example.valorantapp.features.agents.presentation.mappers

import com.example.valorantapp.features.agents.domain.entity.AgentEntity
import com.example.valorantapp.features.agents.presentation.model.AgentModel

fun AgentEntity.toAgentModel(): AgentModel =
    AgentModel(
        agentUuid = this.agentUuid,
        name = this.name,
        portrait = this.portrait,
        roleName = this.roleName,
        roleIcon = this.roleIcon
    )
