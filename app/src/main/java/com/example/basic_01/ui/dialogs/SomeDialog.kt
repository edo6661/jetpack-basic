package com.example.basic_01.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SomeDialog() {
  Column(
    modifier = Modifier
      .background(Color.White)
      .size(200.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text("Dialog Title")
    Text("Dialog Content")
  }
}