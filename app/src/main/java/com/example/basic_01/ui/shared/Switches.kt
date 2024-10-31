package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun SwitchMinimalExample() {
  var checked by remember { mutableStateOf(true) }

  Switch(
    checked = checked,
    onCheckedChange = {
      checked = it
    }
  )
}

@Composable
fun SwitchWithIconExample() {
  var checked by remember { mutableStateOf(true) }

  Switch(
    checked = checked,
    onCheckedChange = {
      checked = it
    },
    thumbContent = if (checked) {
      {
        Icon(
          imageVector = Icons.Filled.Check,
          contentDescription = null,
          modifier = Modifier.size(SwitchDefaults.IconSize),
        )
      }
    } else {
      null
    }
  )
}

@Composable
fun SwitchWithCustomColors() {
  var checked by remember { mutableStateOf(true) }

  Switch(
    checked = checked,
    onCheckedChange = {
      checked = it
    },
    colors = SwitchDefaults.colors(
      checkedThumbColor = MaterialTheme.colorScheme.primary,
      checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
      uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
      uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
    )
  )
}


@Composable
fun AllSwitches() {
  Row {
    SwitchMinimalExample()
    SwitchWithIconExample()
    SwitchWithCustomColors()
  }
}