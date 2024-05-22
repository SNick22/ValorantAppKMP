package com.example.valorantapp.features.agents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.valorantapp.core.design.MyApplicationTheme
import com.example.valorantapp.core.resources.strings.LocalStringPool
import com.example.valorantapp.features.agents.presentation.AgentsAction
import com.example.valorantapp.features.agents.presentation.AgentsEvent
import com.example.valorantapp.features.agents.presentation.AgentsState
import com.example.valorantapp.features.agents.presentation.AgentsViewModel
import com.example.valorantapp.features.agents.presentation.model.AgentModel

@Composable
fun AgentsScreen(viewModel: AgentsViewModel = viewModel()) {
    val screenState by viewModel.viewStates.collectAsStateWithLifecycle()
    val screenAction by viewModel.viewActions.collectAsStateWithLifecycle(null)

    AgentsActions(screenAction)

    AgentsContent(
        screenState = screenState,
        onEvent = remember { viewModel::obtainEvent }
    )
}

@Composable
private fun AgentsActions(action: AgentsAction?) {
    LaunchedEffect(action) {
        when (action) {
            is AgentsAction.NavigateToAgentDetails -> {

            }
            null -> Unit
        }
    }
}

@Composable
private fun AgentsContent(
    screenState: AgentsState,
    onEvent: (AgentsEvent) -> Unit
) {
    Scaffold(
        topBar = {
            AgentsTopBar(
                onExitClick = {  }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (screenState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(screenState.agents) { agent ->
                        AgentCard(
                            onClick = { onEvent(AgentsEvent.OnAgentClick(agent)) },
                            name = agent.name,
                            portrait = agent.portrait,
                            roleName = agent.roleName,
                            roleIcon = agent.roleIcon,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AgentsTopBar(
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = LocalStringPool.current.agentsTopBarTitle) },
        actions = {
            IconButton(onClick = onExitClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ExitToApp,
                    contentDescription = "Exit"
                )
            }
        },
        modifier = modifier
    )
}

@Composable
private fun AgentCard(
    onClick: () -> Unit,
    name: String,
    portrait: String,
    roleName: String?,
    roleIcon: String?,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = portrait,
                contentDescription = "portrait"
            )

            Column {
                Text(text = name, fontWeight = FontWeight.Bold)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (roleIcon != null) {
                        AsyncImage(
                            model = roleIcon,
                            contentDescription = "role",
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    if (roleName != null) {
                        Text(text = roleName)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AgentsPreview() {
    MyApplicationTheme {
        AgentsContent(
            screenState = AgentsState(
                agents = (0..2).map {
                    AgentModel(
                        agentUuid = "",
                        name = "Sova",
                        portrait = "",
                        roleName = "Support",
                        roleIcon = null
                    )
                },
                isLoading = false
            ),
            onEvent = {}
        )
    }
}
