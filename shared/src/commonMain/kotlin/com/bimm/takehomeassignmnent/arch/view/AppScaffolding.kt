package com.bimm.takehomeassignmnent.arch.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bimm.takehomeassignmnent.arch.Event
import com.bimm.takehomeassignmnent.arch.SnackBarEvent
import kmptakehomeassignment.shared.generated.resources.Res
import kmptakehomeassignment.shared.generated.resources.arrow_back
import kotlinx.coroutines.flow.SharedFlow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppScaffolding(
    title: String? = null,
    events: SharedFlow<Event>,
    onBackNavigation: (() -> Unit)? = null,
    content: @Composable () -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessageKey by remember { mutableStateOf<StringResource?>(null) }

    LaunchedEffect(events) {
        events.collect { event ->
            if(event is SnackBarEvent) {
                snackbarMessageKey = event.messageKey
            }
        }
    }

    snackbarMessageKey?.let {
        val message = stringResource(it)
        LaunchedEffect(snackbarMessageKey) {
            snackbarHostState.showSnackbar(message)
            snackbarMessageKey  = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                Modifier.fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surface),
                verticalAlignment = Alignment.CenterVertically
            ) {
                onBackNavigation?.let {
                    IconButton(
                        modifier = Modifier.padding(12.dp),
                        onClick = it,
                    ){
                       Icon(
                           modifier = Modifier.size(24.dp),
                           painter = painterResource(Res.drawable.arrow_back),
                           contentDescription = null,
                           tint = MaterialTheme.colorScheme.onSurface
                       )
                    }
                }
                title?.let {
                    Text(
                        modifier = Modifier.padding( 16.dp),
                        text = title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 48.dp)
            ){
                content()
            }
        }
    )
}



