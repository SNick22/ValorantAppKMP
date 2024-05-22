package com.example.valorantapp.core.viewmodel

import com.example.valorantapp.core.CommonStateFlow
import com.example.valorantapp.core.asCommonSharedFlow
import com.example.valorantapp.core.asCommonStateFlow
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Action, Event>(
    initState: State
) : KmpViewModel() {

    private val _action = MutableSharedFlow<Action>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val _viewState = MutableStateFlow(initState)

    protected var viewState: State
        get() = _viewState.value
        set(value) {
            _viewState.value = value
        }

    protected var viewAction: Action?
        get() = if (_action.replayCache.isNotEmpty()) {
            _action.replayCache.last()
        } else {
            null
        }
        set(value) {
            scope.launch {
                if (value != null) {
                    _action.emit(value)
                } else {
                    _action.resetReplayCache()
                }
            }
        }

    val viewStates: CommonStateFlow<State>
        get() = _viewState.asCommonStateFlow()

    val viewActions: SharedFlow<Action>
        get() = _action.asCommonSharedFlow()

    abstract fun obtainEvent(event: Event)
}
