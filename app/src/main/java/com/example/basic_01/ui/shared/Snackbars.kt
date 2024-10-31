package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun BasicSnackbar() {
  val scope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }
  Scaffold(
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        text = { Text("Show snackbar") },
        icon = { Icon(Icons.Filled.Create, contentDescription = "Show Snackbar") },
        onClick = {
          scope.launch {
            snackbarHostState.showSnackbar("Snackbar")
          }
        }
      )
    }
  ) { contentPadding ->
    // Add content here if needed
    Column(modifier = Modifier.padding(contentPadding)) {
      // Content can go here, currently just a placeholder
      Text("This is a basic Snackbar example.")
    }
  }
}

@Composable
fun BasicSnackbarAction() {
  val scope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }
  Scaffold(
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState)
    },
    floatingActionButton = {
      ExtendedFloatingActionButton(
        text = { Text("Show snackbar") },
        icon = { Icon(Icons.Filled.Create, contentDescription = "Show Snackbar") },
        onClick = {
          scope.launch {
            val result = snackbarHostState.showSnackbar(
              message = "Snackbar",
              actionLabel = "Action",
              duration = SnackbarDuration.Indefinite
            )
            when (result) {
              SnackbarResult.ActionPerformed -> {
                Log.d("Snackbar", "Action performed")
              }

              SnackbarResult.Dismissed       -> {
                Log.d("Snackbar", "Dismissed")
              }
            }
          }
        }
      )
    }
  ) { contentPadding ->
    Column(modifier = Modifier.padding(contentPadding)) {
      // Content for Snackbar with action
      Text("This Snackbar has an action button.")
    }
  }
}

@Composable
fun AllSnackbar() {
  Column {
//    Text("Basic Snackbar Example", style = MaterialTheme.typography.headlineMedium)
//    BasicSnackbar()

    Spacer(modifier = Modifier.height(16.dp))

    Text("Snackbar with Action Example", style = MaterialTheme.typography.headlineMedium)
    BasicSnackbarAction()
  }
}
