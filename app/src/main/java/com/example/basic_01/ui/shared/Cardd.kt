package com.example.basic_01.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardMinimalExample() {
  Card() {
    Text(text = "Hello, world!")
  }
}

@Composable
fun FilledCardExample() {
  Card(
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ),
    modifier = Modifier
      .size(width = 240.dp, height = 100.dp)
  ) {
    Text(
      text = "Filled",
      modifier = Modifier
        .padding(16.dp),
      textAlign = TextAlign.Center,
    )
  }
}

@Composable
fun ElevatedCardExample() {
  ElevatedCard(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 6.dp
    ),
    modifier = Modifier
      .size(width = 240.dp, height = 100.dp)
  ) {
    Text(
      text = "Elevated",
      modifier = Modifier
        .padding(16.dp),
      textAlign = TextAlign.Center,
    )
  }
}

@Composable
fun OutlinedCardExample() {
  OutlinedCard(
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface,
    ),
    border = BorderStroke(1.dp, Color.Black),
    modifier = Modifier
      .size(width = 240.dp, height = 100.dp)
  ) {
    Text(
      text = "Outlined",
      modifier = Modifier
        .padding(16.dp),
      textAlign = TextAlign.Center,
    )
  }
}


@Preview(showSystemUi = true)
@Composable
fun allCards() {
  Column() {
    CardMinimalExample()
    FilledCardExample()
    ElevatedCardExample()
    OutlinedCardExample()
  }
}