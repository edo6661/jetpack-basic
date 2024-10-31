package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BadgeExample() {
  BadgedBox(
    badge = {
      Badge(
        
      )
    }
  ) {
    Icon(
      imageVector = Icons.Filled.MailOutline,
      contentDescription = "Email"
    )
  }
}

@Composable
fun BadgeInteractiveExample() {
  var itemCount by remember { mutableStateOf(0) }

  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    BadgedBox(
      badge = {
        if (itemCount > 0) {
          Badge(
            containerColor = Color.Red,
            contentColor = Color.White
          ) {
            Text("$itemCount")
          }
        }
      }
    ) {
      Icon(
        imageVector = Icons.Filled.ShoppingCart,
        contentDescription = "Shopping cart",
      )
    }
    Button(onClick = { itemCount ++ }) {
      Text("Add item")
    }
  }
}

@Composable
fun AllBadgesExample() {
  Column(
    modifier = Modifier.padding(
      top = 16.dp,
    )
  ) {
    BadgeExample()
    BadgeInteractiveExample()
  }
}
