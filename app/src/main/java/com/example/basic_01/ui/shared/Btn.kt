package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showSystemUi = true)
@Composable
fun Btn() {
  Button(
    onClick = {
      Log.d("Btn", "Button Clicked")
    },
    shape = RoundedCornerShape(16.dp),

  ) {
    Text("Click Me")

  }
}