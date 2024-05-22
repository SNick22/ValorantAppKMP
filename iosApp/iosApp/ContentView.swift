import SwiftUI
import shared
import Combine

struct ContentView: View {
    
    let viewModel: AgentsViewModel
    @State private var viewState: AgentsState
    
    init() {
        viewState = AgentsState(isLoading: false, agents: [])
        viewModel = AgentsViewModel()
    }
    
    private func setViewState(state: AgentsState) {
        viewState = state
    }
    
    var body: some View {
        ScrollView {
            VStack {
                ForEach(viewState.agents, id: \.self.agentUuid) { agent in
                    HStack(alignment: .top) {
                        AsyncImage(url: URL(string: agent.portrait), scale: 3)
                        VStack(alignment: .leading) {
                            Text(agent.name)
                            HStack {
                                if let roleIcon = agent.roleIcon {
                                    AsyncImage(url: URL(string: roleIcon), scale: 4)
                                }
                                if let roleName = agent.roleName {
                                    Text(roleName)
                                }
                            }
                        }
                        Spacer()
                    }
                    .padding(8)
                    .frame(maxWidth: .infinity)
                    .background(Color.mint)
                    .clipShape(RoundedRectangle(cornerRadius: 3))
                    .padding(.horizontal, 8)
                    .onTapGesture {
                        viewModel.obtainEvent(event: AgentsEvent.OnAgentClick(agent: agent))
                    }
                }
            }
        }
        .onReceive(statePublisher(viewModel.viewStates), perform: { state in
            setViewState(state: state)
        })
        .frame(width: UIScreen.main.bounds.width)
    }
}
