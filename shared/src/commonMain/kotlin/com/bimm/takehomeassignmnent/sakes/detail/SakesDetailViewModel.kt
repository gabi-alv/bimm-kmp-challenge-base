package com.bimm.takehomeassignmnent.sakes.detail

import com.bimm.takehomeassignmnent.arch.BaseViewModel
import com.bimm.takehomeassignmnent.arch.NavigateBackEvent
import com.bimm.takehomeassignmnent.arch.OpenExternalBrowserEvent
import com.bimm.takehomeassignmnent.sakes.SakesRoute
import com.bimm.takehomeassignmnent.sakes.data.SakesRepository
import com.bimm.takehomeassignmnent.sakes.detail.model.SakesDetailIntent
import com.bimm.takehomeassignmnent.sakes.detail.model.SakesDetailState
import kotlinx.coroutines.launch


class SakesDetailViewModel(
    private val repository: SakesRepository
): BaseViewModel<SakesDetailState, SakesDetailIntent>() {

    override fun initialState(): SakesDetailState {
        val route: SakesRoute.SakesDetail = currentRoute()
        return SakesDetailState(
            id = route.id
        )
    }

    override fun onIntent(intent: SakesDetailIntent) {
        when(intent){
            SakesDetailIntent.InitializeScreenIntent -> scope.launch {
                setState { state.value.copy(isLoading = true) }
                val result = repository.getSakeShop(state.value.id)
                setState {
                    state.value.copy(
                        data = result.getOrNull(),
                        isLoading = false,
                        loadingError = result.exceptionOrNull()
                    )
                }
            }
            is SakesDetailIntent.OpenExternalWebsiteIntent -> {
                sendEvent(OpenExternalBrowserEvent(intent.url))
            }
            is SakesDetailIntent.OpenGoogleMapsIntent -> {
                sendEvent(OpenExternalBrowserEvent(intent.googleUrl))
            }

            SakesDetailIntent.NavigateBackIntent -> sendEvent(NavigateBackEvent)
        }
    }
}