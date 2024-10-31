package com.example.basic_01.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun ProfileScreen(
  name : String
) {
  Row {
    Text("Profile, Name: $name")

  }
}