package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Example(onClick : () -> Unit) {
  FloatingActionButton(
    onClick = { onClick() },
    containerColor = Color.Red,
    contentColor = Color.Blue,

  ) {
    Icon(Icons.Filled.Add, "Floating action button.")
  }
}

@Composable
fun SmallExample(onClick : () -> Unit) {
  SmallFloatingActionButton(
    onClick = { onClick() },
    containerColor = MaterialTheme.colorScheme.secondaryContainer,
    contentColor = MaterialTheme.colorScheme.secondary
  ) {
    Icon(Icons.Filled.Add, "Small floating action button.")
  }
}

@Composable
fun LargeExample(onClick : () -> Unit) {
  LargeFloatingActionButton(
    onClick = { onClick() },
    shape = CircleShape,
  ) {
    Icon(Icons.Filled.Add, "Large floating action button")
  }
}

@Preview(showSystemUi = true)
@Composable
fun previewAllFab() {
  Row {
    Example(onClick = {})
    SmallExample(onClick = {})
    LargeExample(onClick = {})
  }
}


