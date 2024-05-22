package com.example.valorantapp.features.agents.data.source.model

import kotlinx.serialization.Serializable

@Serializable
data class AgentResponse(
    val uuid: String,
    val displayName: String,
    val description: String,
    val developerName: String,
    val characterTags: List<String>? = null,
    val displayIcon: String,
    val bustPortrait: String,
    val fullPortrait: String,
    val assetPath: String,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val isAvailableForTest: Boolean,
    val role: AgentRoleResponse? = null,
    val abilities: List<AgentAbilityResponse>

)

@Serializable
data class AgentAbilityResponse(
    val slot: String,
    val displayName: String? = null,
    val description: String? = null,
    val displayIcon: String? = null
)

@Serializable
data class AgentRoleResponse(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val assetPath: String
)
