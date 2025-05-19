package com.bimm.takehomeassignmnent.sakes

import androidx.compose.runtime.Composable
import androidx.navigation.navigation
import com.bimm.takehomeassignmnent.arch.Route
import com.bimm.takehomeassignmnent.arch.view.NavGraphScope
import com.bimm.takehomeassignmnent.arch.view.registerDestination
import com.bimm.takehomeassignmnent.sakes.detail.SakesDetailViewModel
import com.bimm.takehomeassignmnent.sakes.detail.view.SakeDetailScreen
import com.bimm.takehomeassignmnent.sakes.list.SakesListViewModel
import com.bimm.takehomeassignmnent.sakes.list.view.SakeListScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class SakesRoute : Route() {
    @Serializable
    object SakesList : SakesRoute()

    @Serializable
    data class SakesDetail(val id: Int) : SakesRoute()
}

fun NavGraphScope.sakesNavigation() {
    registerDestination<SakesListViewModel>(SakesRoute.SakesList::class) { sakesViewModel ->
        SakeListScreen(
            state = sakesViewModel.state,
            events = sakesViewModel.events,
            onIntent = sakesViewModel::onIntent
        )
    }
    registerDestination<SakesDetailViewModel>(SakesRoute.SakesDetail::class) { sakesDetailViewModel ->
        SakeDetailScreen(
            state = sakesDetailViewModel.state,
            events = sakesDetailViewModel.events,
            onIntent = sakesDetailViewModel::onIntent
        )
    }
}


