package com.bimm.takehomeassignmnent.arch.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.bimm.takehomeassignmnent.arch.BaseViewModel
import com.bimm.takehomeassignmnent.arch.Event
import com.bimm.takehomeassignmnent.arch.NavigateBackEvent
import com.bimm.takehomeassignmnent.arch.NavigationEvent
import com.bimm.takehomeassignmnent.arch.OpenExternalBrowserEvent
import com.bimm.takehomeassignmnent.arch.Route
import com.bimm.takehomeassignmnent.arch.SnackBarEvent
import com.bimm.takehomeassignmnent.arch.openExternalUrl
import com.bimm.takehomeassignmnent.di.DependencyGraph
import com.bimm.takehomeassignmnent.sakes.SakesRoute
import com.bimm.takehomeassignmnent.sakes.sakesNavigation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.qualifier.named
import kotlin.reflect.KClass


@Composable
fun AppNavGraph(){
    MaterialTheme {
        val navHostController = rememberNavController()
        NavHost(
            navController = navHostController,
            startDestination = SakesRoute.SakesList
        ) {
            val navBuilder = this
            with(object : NavGraphScope {
                override val navGraphBuilder: NavGraphBuilder = navBuilder
                override val navHostController: NavHostController = navHostController
            }) {
                sakesNavigation()
            }
        }
    }
}

interface NavGraphScope {
    val navHostController: NavHostController
    val navGraphBuilder: NavGraphBuilder
}


/**
 *  Creates a ViewModel scoped to the current route. Should create a new instance if the
 *  parameters on the object route changes.
 */
inline fun <reified VM : BaseViewModel<*,*>> scopedViewModel(
    navBackStackEntry: NavBackStackEntry,
): VM {
    val koin = DependencyGraph.koin
    val scopeId = navBackStackEntry.destination.route +  navBackStackEntry.arguments
    val scope = koin.getScopeOrNull(scopeId) ?: koin.createScope(scopeId, qualifier = TypeQualifier(VM::class))
    return scope.get()
}


/**
 *  Registers a screen and the events the given ViewModel
 */
inline fun <reified T: BaseViewModel<*,*>> NavGraphScope.registerDestination(
    routeClass: KClass<*>,
    crossinline screen:  @Composable (T) -> Unit,
){
    navGraphBuilder.composable(routeClass) {  navBackStackEntry ->
        val vm = remember { scopedViewModel<T>(navBackStackEntry) }
        vm.route = navBackStackEntry.toRoute(routeClass)
        screen(vm)
        val events = vm.events
        LaunchedEffect(events) {
         events.collect { event ->
              when(event){
                  NavigateBackEvent -> navHostController.navigateUp()
                  is NavigationEvent -> navHostController.navigate(route = event.route)
                  is OpenExternalBrowserEvent -> openExternalUrl(event.url)
                  else -> Unit // Nothing else to do at this level
                  /**
                   *  Note: other OS low level events could be handled here with a SystemEventHandler
                   *  class with expect / actual APIs for the specific events we want to handle.
                   */
              }
          }
      }
  }
}