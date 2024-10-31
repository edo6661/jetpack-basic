package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AssistChipExample() {
  AssistChip(
    onClick = { Log.d("Assist chip", "hello world") },
    label = { Text("Assist chip") },
    leadingIcon = {
      Icon(
        Icons.Filled.Settings,
        contentDescription = "Localized description",
        Modifier.size(AssistChipDefaults.IconSize)
      )
    }
  )
}


@Composable
fun FilterChipExample() {
  var selected by remember { mutableStateOf(false) }

  FilterChip(
    onClick = { selected = ! selected },
    label = { Text("Filter chip") },
    selected = selected,
    leadingIcon = if (selected) {
      {
        Icon(
          imageVector = Icons.Filled.Done,
          contentDescription = "Done icon",
          modifier = Modifier.size(FilterChipDefaults.IconSize)
        )
      }
    } else {
      {
        Icon(
          imageVector = Icons.Filled.Person,
          contentDescription = "Person icon",
          modifier = Modifier.size(FilterChipDefaults.IconSize)
        )
      }
    },
  )
}


@Composable
fun InputChipExample(
  text : String,
  onDismiss : () -> Unit,
) {
  var enabled by remember { mutableStateOf(true) }

  InputChip(
    onClick = {
      onDismiss()
      enabled = ! enabled
    },
    label = { Text(text) },
    selected = enabled,
    // left icon
    avatar = {
      if (enabled) {
        Icon(
          Icons.Filled.Person,
          contentDescription = "Localized description",
          Modifier.size(InputChipDefaults.AvatarSize)
        )
      } else {
        Icon(
          Icons.Filled.Close,
          contentDescription = "Localized description",
          Modifier.size(InputChipDefaults.AvatarSize)
        )
      }
    },
    // right icon
    trailingIcon = {
      if (enabled) {
        Icon(
          Icons.Filled.Close,
          contentDescription = "Localized description",
          Modifier.size(InputChipDefaults.IconSize)
        )
      } else {
        Icon(
          Icons.Filled.Done,
          contentDescription = "Localized description",
          Modifier.size(InputChipDefaults.IconSize)
        )
      }
    },
  )
}

@Composable
fun SuggestionChipExample() {
  SuggestionChip(
    onClick = { Log.d("Suggestion chip", "hello world") },
    label = { Text("Suggestion chip") }
  )
}


@Composable
@Preview(showSystemUi = true)
fun ChipsExample() {
  Column() {
    AssistChipExample()
    FilterChipExample()
    InputChipExample("Input chip") { }
    SuggestionChipExample()
  }
}
