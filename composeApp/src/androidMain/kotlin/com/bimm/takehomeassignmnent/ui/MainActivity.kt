package com.bimm.takehomeassignmnent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bimm.takehomeassignmnent.arch.view.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            AppNavGraph()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppNavGraph()
}