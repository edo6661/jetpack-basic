package com.example.basic_01.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.basic_01.navigations.Screen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun HomeScreen(navController : NavController) {
  val sendedName = "someName"
  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Text("Home Screen")
    Button(
      onClick = {
        navController.navigate(
          Screen.Profile.createRoute(sendedName)
        )
      }
    ) {
      Text("Go to profile")
    }
    Button(
      onClick = {
        navController.navigate("someDialog")
      }
    ) {
      Text("Open Dialog")
    }

  }
}