package com.example.valorantapp.features.agents.data.source

import com.example.valorantapp.core.network.BaseModel
import com.example.valorantapp.features.agents.data.source.model.AgentResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class AgentRemoteSource(
    private val client: HttpClient
) {

    suspend fun getAgents(): BaseModel<List<AgentResponse>> {
        return client.get {
            url("agents")
            parameter("isPlayableCharacter", true)
        }.body()
    }
}
