package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(
  onConfirm : (TimePickerState) -> Unit,
  onDismiss : () -> Unit,
) {
  val currentTime = Calendar.getInstance()

  val timePickerState = rememberTimePickerState(
    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
    initialMinute = currentTime.get(Calendar.MINUTE),
  )

  Column() {
    TimePicker(
      state = timePickerState,
    )
    Button(onClick = onDismiss) {
      Text("Dismiss picker")
    }
    Button(onClick =
    { onConfirm(timePickerState) }
    ) {
      Text("Confirm selection")
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputExample(
  onConfirm : (TimePickerState) -> Unit,
  onDismiss : () -> Unit,
) {
  val currentTime = Calendar.getInstance()

  val timePickerState = rememberTimePickerState(
    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
    initialMinute = currentTime.get(Calendar.MINUTE),
  )

  Column {
    TimeInput(
      state = timePickerState,
    )
    Button(onClick = onDismiss) {
      Text("Dismiss picker")
    }
    Button(onClick =
    { onConfirm(timePickerState) }
    ) {
      Text("Confirm selection")
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePickerExample(
  onConfirm : (TimePickerState) -> Unit,
  onDismiss : () -> Unit,
) {
  val currentTime = Calendar.getInstance()
  val timePickerState = rememberTimePickerState(
    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
    initialMinute = currentTime.get(Calendar.MINUTE),
  )
  var showDial by remember { mutableStateOf(true) }
  val toggleIcon = if (showDial) {
    Icons.Filled.Call
  } else {
    Icons.Filled.Done
  }

  AlertDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(onClick = { onConfirm(timePickerState) }) {
        Text("OK")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    },
    title = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Select Time")
        IconButton(onClick = { showDial = ! showDial }) {
          Icon(
            imageVector = toggleIcon,
            contentDescription = "Time picker type toggle"
          )
        }
      }
    },
    text = {
      if (showDial) {
        TimePicker(state = timePickerState)
      } else {
        TimeInput(state = timePickerState)
      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTimePickers() {
  var showDialPicker by remember { mutableStateOf(false) }
  var showInputPicker by remember { mutableStateOf(false) }
  var showAdvancedPicker by remember { mutableStateOf(false) }

  Column {
    // Button untuk membuka DialExample
    Button(onClick = { showDialPicker = true }) {
      Text("Show Dial Example")
    }
    // Button untuk membuka InputExample
    Button(onClick = { showInputPicker = true }) {
      Text("Show Input Example")
    }
    // Button untuk membuka AdvancedTimePickerExample
    Button(onClick = { showAdvancedPicker = true }) {
      Text("Show Advanced Time Picker Example")
    }
    if (showDialPicker) {
      DialExample(
        onConfirm = { timePickerState ->
          Log.d("DialExample", "Selected time: ${timePickerState.hour}:${timePickerState.minute}")
          showDialPicker = false
        },
        onDismiss = {
          showDialPicker = false
        }
      )
    }

    if (showInputPicker) {
      InputExample(
        onConfirm = { timePickerState ->
          Log.d("InputExample", "Selected time: ${timePickerState.hour}:${timePickerState.minute}")
          showInputPicker = false
        },
        onDismiss = {
          showInputPicker = false
        }
      )
    }



    if (showAdvancedPicker) {
      AdvancedTimePickerExample(
        onConfirm = { timePickerState ->
          Log.d(
            "AdvancedTimePickerExample",
            "Selected time: ${timePickerState.hour}:${timePickerState.minute}"
          )
          showAdvancedPicker = false
        },
        onDismiss = {
          showAdvancedPicker = false
        }
      )
    }

  }
}
