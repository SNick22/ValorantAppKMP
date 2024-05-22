package com.example.valorantapp.core

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface Watchable<T> {

    fun watch(onNext: (T) -> Unit): Closeable
}

open class CommonFlow<T> internal constructor(
    private val flow: Flow<T>
): Watchable<T>, Flow<T> by flow {

    override fun watch(
        onNext: (T) -> Unit,
    ): Closeable {
        val job = MainScope().launch {
            flow.collect {
                onNext(it)
            }
        }

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonStateFlow<T> internal constructor(
    private val stateFlow: StateFlow<T>
): CommonFlow<T>(stateFlow), StateFlow<T> by stateFlow {

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        stateFlow.collect(collector)
    }
}

fun <T> StateFlow<T>.asCommonStateFlow(): CommonStateFlow<T> = CommonStateFlow(this)

class CommonSharedFlow<T> internal constructor(
    private val sharedFlow: SharedFlow<T>
): CommonFlow<T>(sharedFlow), SharedFlow<T> by sharedFlow {

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        sharedFlow.collect(collector)
    }
}

fun <T> SharedFlow<T>.asCommonSharedFlow(): CommonSharedFlow<T> = CommonSharedFlow(this)
