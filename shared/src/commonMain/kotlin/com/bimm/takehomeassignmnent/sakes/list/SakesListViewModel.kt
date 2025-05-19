package com.bimm.takehomeassignmnent.sakes.list

import com.bimm.takehomeassignmnent.arch.BaseViewModel
import com.bimm.takehomeassignmnent.arch.NavigationEvent
import com.bimm.takehomeassignmnent.arch.OpenExternalBrowserEvent
import com.bimm.takehomeassignmnent.sakes.SakesRoute
import com.bimm.takehomeassignmnent.sakes.data.SakesRepository
import com.bimm.takehomeassignmnent.sakes.list.model.SakeShopListUiItem
import com.bimm.takehomeassignmnent.sakes.list.model.SakesListIntent
import com.bimm.takehomeassignmnent.sakes.list.model.SakesListState
import kotlinx.coroutines.launch

class SakesListViewModel(
    private val sakesRepository: SakesRepository
) : BaseViewModel<SakesListState, SakesListIntent>() {
    override fun initialState(): SakesListState = SakesListState()

    override fun onIntent(intent: SakesListIntent) {
        when (intent) {
            is SakesListIntent.ClickSakeShopIntent -> sendEvent(
                NavigationEvent(
                    SakesRoute.SakesDetail(id = intent.id)
                )
            )
            SakesListIntent.InitScreenIntent -> {
                scope.launch {
                    setState { state.value.copy(isLoading = true) }
                       val result = sakesRepository.getSakeShops()
                       val sakesItems = result.getOrNull()?.mapIndexed { index, sakeShopModel ->
                                SakeShopListUiItem(
                                    id = index,
                                    name = sakeShopModel.name,
                                    picture = sakeShopModel.picture,
                                    rating = sakeShopModel.rating,
                                    description = sakeShopModel.description
                                )
                            }.orEmpty()
                        setState {
                            state.value.copy(
                                isLoading = false,
                                data = sakesItems,
                                loadingError = result.exceptionOrNull()
                            )
                        }
                }
            }
            is SakesListIntent.OpenSakeshopWebsiteIntent -> {
                sendEvent(OpenExternalBrowserEvent(intent.url))
            }
        }
    }
}