package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialWithDialogExample(
  onConfirm : (TimePickerState) -> Unit,
  onDismiss : () -> Unit,
) {
  val currentTime = Calendar.getInstance()

  val timePickerState = rememberTimePickerState(
    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
    initialMinute = currentTime.get(Calendar.MINUTE),
    is24Hour = true,
  )

  TimePickerDialog(
    onDismiss = { onDismiss() },
    onConfirm = { onConfirm(timePickerState) }
  ) {
    TimePicker(
      state = timePickerState,
    )
  }
}

@Composable
fun TimePickerDialog(
  onDismiss : () -> Unit,
  onConfirm : () -> Unit,
  content : @Composable () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    dismissButton = {
      TextButton(onClick = { onDismiss() }) {
        Text("Dismiss")
      }
    },
    confirmButton = {
      TextButton(onClick = { onConfirm() }) {
        Text("OK")
      }
    },
    text = { content() }
  )
}

@Composable
fun AdvancedTimePickerDialog(
  title : String = "Select Time",
  onDismiss : () -> Unit,
  onConfirm : () -> Unit,
  toggle : @Composable () -> Unit = {},
  content : @Composable () -> Unit,
) {
  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false),
  ) {
    Surface(
      shape = MaterialTheme.shapes.extraLarge,
      tonalElevation = 6.dp,
      modifier =
      Modifier
        .width(IntrinsicSize.Min)
        .height(IntrinsicSize.Min)
        .background(
          shape = MaterialTheme.shapes.extraLarge,
          color = MaterialTheme.colorScheme.surface
        ),
    ) {
      Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
          text = title,
          style = MaterialTheme.typography.labelMedium
        )
        content()
        Row(
          modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
        ) {
          toggle()
          Spacer(modifier = Modifier.weight(1f))
          TextButton(onClick = onDismiss) { Text("Cancel") }
          TextButton(onClick = onConfirm) { Text("OK") }
        }
      }
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTimePickersDialog() {
  var showDialPicker by remember { mutableStateOf(false) }
  var showAdvancedPicker by remember { mutableStateOf(false) }

  Column {
    // Button untuk membuka DialWithDialogExample
    Button(onClick = { showDialPicker = true }) {
      Text("Show Dial Example")
    }

    // Button untuk membuka AdvancedTimePickerExample
    Button(onClick = { showAdvancedPicker = true }) {
      Text("Show Advanced Time Picker Example")
    }

    // Tampilkan Dialog jika showDialPicker true
    if (showDialPicker) {
      DialWithDialogExample(
        onConfirm = { timePickerState ->
          // Tindakan saat mengkonfirmasi
          Log.d("DialExample", "Selected Time: ${timePickerState.hour}:${timePickerState.minute}")
          showDialPicker = false
        },
        onDismiss = {
          // Tindakan saat membatalkan
          showDialPicker = false
        }
      )
    }

    // Tampilkan Dialog jika showAdvancedPicker true
    if (showAdvancedPicker) {
      AdvancedTimePickerExample(
        onConfirm = { timePickerState ->
          // Tindakan saat mengkonfirmasi
          Log.d(
            "AdvancedTimePicker",
            "Selected Time: ${timePickerState.hour}:${timePickerState.minute}"
          )
          showAdvancedPicker = false
        },
        onDismiss = {
          // Tindakan saat membatalkan
          showAdvancedPicker = false
        }
      )
    }
  }
}

