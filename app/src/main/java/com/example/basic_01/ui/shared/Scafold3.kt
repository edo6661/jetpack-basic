package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigationExample(
  navigateBack : () -> Unit,
) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(
            "Navigation example",
          )
        },
        navigationIcon = {
          IconButton(onClick = navigateBack) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Localized description"
            )
          }
        },
      )
    },
  ) { innerPadding ->
    Text(
      "Click the back button to pop from the back stack.",
      modifier = Modifier.padding(innerPadding),
    )
  }
}
