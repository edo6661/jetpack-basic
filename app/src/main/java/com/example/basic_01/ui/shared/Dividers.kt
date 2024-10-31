package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDividerExample() {
  Column(
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    Text("First item in list")
    HorizontalDivider(thickness = 2.dp)
    Text("Second item in list")
  }
}


@Composable
fun VerticalDividerExample() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min),
    horizontalArrangement = Arrangement.SpaceEvenly
  ) {
    Text("First item in row")
    VerticalDivider(color = MaterialTheme.colorScheme.secondary)
    Text("Second item in row")
  }
}

@Composable
fun AllDividers() {
  Column {
    HorizontalDividerExample()
    VerticalDividerExample()
  }
}