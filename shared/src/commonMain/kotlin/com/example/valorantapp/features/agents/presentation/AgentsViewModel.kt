package com.example.valorantapp.features.agents.presentation

import com.example.valorantapp.core.viewmodel.BaseViewModel
import com.example.valorantapp.di.PlatformSdk
import com.example.valorantapp.features.agents.domain.usecase.GetAgentsUseCase
import com.example.valorantapp.features.agents.presentation.mappers.toAgentModel
import com.example.valorantapp.features.agents.presentation.model.AgentModel
import kotlinx.coroutines.launch

class AgentsViewModel: BaseViewModel<AgentsState, AgentsAction, AgentsEvent>(
    initState = AgentsState()
) {

    private val getAgentsUseCase by PlatformSdk.lazyInstance<GetAgentsUseCase>()

    init {
        loadAgents()
    }

    override fun obtainEvent(event: AgentsEvent) {
        when (event) {
            is AgentsEvent.OnAgentClick -> onAgentClick(event.agent)
        }
    }

    private fun onAgentClick(agent: AgentModel) {
        viewAction = AgentsAction.NavigateToAgentDetails(agent.agentUuid)
    }

    private fun loadAgents() {
        scope.launch {
            try {
                viewState = viewState.copy(isLoading = true)
                val agents = getAgentsUseCase()
                    .map { it.toAgentModel() }
                viewState = viewState.copy(agents = agents, isLoading = false)
            } catch (e: Throwable) {
                e.printStackTrace()
                viewState = viewState.copy(isLoading = false)
            }
        }
    }
}
