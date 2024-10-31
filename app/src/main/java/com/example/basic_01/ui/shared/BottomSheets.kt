package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheets() {

  val sheetState = rememberModalBottomSheetState()
  val scope = rememberCoroutineScope()
  var showBottomSheet by remember { mutableStateOf(false) }

  // Remember state for checkbox
  var checked by remember { mutableStateOf(false) }

  Scaffold(
    floatingActionButton = {
      ExtendedFloatingActionButton(
        text = { Text("Show bottom sheet") },
        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
        onClick = {
          showBottomSheet = true
        }
      )
    }
  ) { contentPadding ->
    // Screen content

    if (showBottomSheet) {
      ModalBottomSheet(
        onDismissRequest = {
          showBottomSheet = false
        },
        modifier = Modifier.padding(contentPadding),
        sheetState = sheetState
      ) {
        // Content inside the bottom sheet
        Column(
          modifier = Modifier.padding(16.dp)
        ) {
          Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
          )

          // Example Checkbox
          Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
              checked = checked,
              onCheckedChange = { checked = it }
            )
            Text(text = "Enable notifications")
          }

          Spacer(modifier = Modifier.padding(8.dp))

          // Example button to hide bottom sheet
          Button(onClick = {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
              if (! sheetState.isVisible) {
                showBottomSheet = false
              }
            }
          }) {
            Text("Hide bottom sheet")
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet() {
  var showBottomSheet by remember { mutableStateOf(false) }
  val sheetState = rememberModalBottomSheetState(
    skipPartiallyExpanded = false,
  )

  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Button(
      onClick = { showBottomSheet = true }
    ) {
      Text("Display partial bottom sheet")
    }

    if (showBottomSheet) {
      ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = { showBottomSheet = false }
      ) {
        Text(
          "Swipe up to open sheet. Swipe down to dismiss.",
          modifier = Modifier.padding(16.dp)
        )
      }
    }
  }
}

