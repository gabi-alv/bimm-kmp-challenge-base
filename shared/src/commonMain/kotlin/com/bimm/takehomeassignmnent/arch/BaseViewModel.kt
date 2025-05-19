package com.bimm.takehomeassignmnent.arch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * This summarize the most important aspects of the MVI architecture used in this project.
 * User inputs are recorded as Intents that are unique for our screens, and a State is modified
 * as a result.
 *
 * Events are the ephemeral actions that may happen as a result of our business logic, like
 * showing a Snackbar or navigating.
 */

abstract class BaseViewModel<S, I> {

    protected val scope = CoroutineScope(Dispatchers.Main)

    lateinit var route: Route

    private val _state: MutableStateFlow<S> by lazy {
        MutableStateFlow(initialState())
    }
    private val _events = MutableSharedFlow<Event>()

    val state: StateFlow<S>
        get() = _state

    val events: SharedFlow<Event> = _events

    protected abstract fun initialState(): S

    protected fun setState(updater: S.() -> S) {
        _state.value = _state.value.updater()
    }

    protected fun sendEvent(event: Event) {
        scope.launch { _events.emit(event) }
    }

    protected fun <T: Route> currentRoute(): T {
        return route as T
    }

    abstract fun onIntent(intent: I)
}
