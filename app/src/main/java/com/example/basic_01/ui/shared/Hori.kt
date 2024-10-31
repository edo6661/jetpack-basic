package com.example.basic_01.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun Hori() {
  Row(
    modifier = Modifier
      .background(
        color = Color.Red
      )
      .padding(16.dp)
      .fillMaxWidth()
      .height(100.dp)
    ,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    for (i in 0..2) {
      Box(
        modifier = Modifier
          .background(
            color = Color.Cyan
          )
          .size(75.dp)
          .wrapContentHeight(align = Alignment.CenterVertically)
          .wrapContentWidth(align = Alignment.CenterHorizontally)

      ) {
        Text(
          text = "Item $i",
          color = Color.White
        )
      }
    }

  }
}