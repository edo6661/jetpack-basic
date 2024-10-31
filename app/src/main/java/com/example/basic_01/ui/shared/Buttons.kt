package com.example.basic_01.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilledButtonExample() {
  Button(
    onClick = {},
    enabled = false,
    colors = ButtonColors(
      contentColor = Color.Red,
      containerColor = Color.Green,
      disabledContentColor = Color.Red,
      disabledContainerColor = Color.Gray
    ),
//    shape = RoundedCornerShape(16.dp),
    shape = RectangleShape,
    border = BorderStroke(
      width = 2.dp,
      color = Color.Blue,

      ),
    contentPadding = PaddingValues(36.dp),
  ) {
    Text("Filled")
  }
}

@Composable
fun FilledTonalButtonExample(onClick : () -> Unit) {
  FilledTonalButton(onClick = { onClick() }) {
    Text("Tonal")
  }
}

@Composable
fun OutlinedButtonExample(onClick : () -> Unit) {
  OutlinedButton(onClick = { onClick() }) {
    Text("Outlined")
  }
}

@Composable
fun ElevatedButtonExample(onClick : () -> Unit) {
  ElevatedButton(onClick = { onClick() }) {
    Text("Elevated")
  }
}

@Preview(showSystemUi = true)
@Composable
fun allButtons() {
  Column {
    FilledButtonExample()
    FilledTonalButtonExample({})
    OutlinedButtonExample({})
    ElevatedButtonExample({})
  }
}

