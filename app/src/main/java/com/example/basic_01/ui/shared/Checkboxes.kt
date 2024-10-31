package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.state.ToggleableState

@Composable
fun CheckboxMinimalExample() {
  var checked by remember { mutableStateOf(true) }

  Row(
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      "Minimal checkbox"
    )
    Checkbox(
      checked = checked,
      onCheckedChange = { checked = it }
    )
  }

  Text(
    if (checked) "Checkbox is checked" else "Checkbox is unchecked"
  )
}

@Composable
fun CheckboxParentExample() {
  // Menginisialisasi state untuk checkbox anak sebagai daftar boolean.
  // Pada awalnya, semua checkbox anak tidak dicentang (false).

  val childCheckedStates = remember { mutableStateListOf(false, false, false) }

  // Menentukan state dari checkbox induk berdasarkan status dari semua checkbox anak.
  // Jika semua checkbox anak dicentang (true), maka induk berstatus On.
  // Jika tidak ada yang dicentang, induk berstatus Off.
  // Jika sebagian checkbox dicentang, induk berstatus Indeterminate (sebagian terpilih).
  val parentState = when {
    childCheckedStates.all { it } -> ToggleableState.On
    childCheckedStates.none { it } -> ToggleableState.Off
    else -> ToggleableState.Indeterminate
  }

  Column() {
    // Parent TriStateCheckbox
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text("Select all")
      // Checkbox induk menggunakan `parentState` sebagai statusnya (On, Off, Indeterminate).

      TriStateCheckbox(
        state = parentState,
        onClick = {
          // Mengubah state semua checkbox anak ketika checkbox induk diklik.
          // kalo parent state ngga on (off / indeterminate), maka kalo di klik bakal jadi on dan begitu juga sebaliknya
          val newState = parentState != ToggleableState.On
          Log.d("CheckboxParentExample", "Parent state clicked: $newState")
          childCheckedStates.forEachIndexed { index, _ ->
            childCheckedStates[index] = newState
          }
        }
      )
    }

    // Looping melalui setiap checkbox anak, menampilkan checkbox dan teks untuk masing-masing.
    childCheckedStates.forEachIndexed { index, checked ->
      Row(
        verticalAlignment = Alignment.CenterVertically,
      ) {
        // Checkbox anak dengan status berdasarkan `checked`.
        // Ketika state dari checkbox anak berubah (dicentang atau tidak), maka state tersebut diperbarui.
        Text("Option ${index + 1}")
        Checkbox(
          checked = checked,
          onCheckedChange = { isChecked ->
            // Update the individual child state
            childCheckedStates[index] = isChecked
          }
        )
      }
    }
  }

  if (childCheckedStates.all { it }) {
    Text("All options selected")
  }
}

@Composable
fun AllCheckboxes() {
  Column {
    CheckboxMinimalExample()
    CheckboxParentExample()
  }
}