package com.example.basic_01.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
  var showDatePicker by remember { mutableStateOf(false) }
  val datePickerState = rememberDatePickerState()
  val selectedDate = datePickerState.selectedDateMillis?.let {
    convertMillisToDate(it)
  } ?: ""

  Box(
    modifier = Modifier.fillMaxWidth()
  ) {
    OutlinedTextField(
      value = selectedDate,
      onValueChange = { },
      label = { Text("DOB") },
      readOnly = true,
      trailingIcon = {
        IconButton(onClick = { showDatePicker = ! showDatePicker }) {
          Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Select date"
          )
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .height(64.dp)
    )

    if (showDatePicker) {
      Popup(
        onDismissRequest = { showDatePicker = false },
        alignment = Alignment.TopStart
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .offset(y = 64.dp)
            .shadow(elevation = 4.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
        ) {
          DatePicker(
            state = datePickerState,
            showModeToggle = false
          )
        }
      }
    }
  }
}

@Composable
fun DatePickerFieldToModal(modifier : Modifier = Modifier, onDateSelected : (Long) -> Unit) {
  var selectedDate by remember { mutableStateOf<Long?>(null) }
  var showModal by remember { mutableStateOf(false) }

  OutlinedTextField(
    value = selectedDate?.let { convertMillisToDate(it) } ?: "",
    onValueChange = { },
    label = { Text("DOB") },
    placeholder = { Text("MM/DD/YYYY") },
    trailingIcon = {
      Icon(Icons.Default.DateRange, contentDescription = "Select date")
    },
    modifier = modifier
      .fillMaxWidth()
      .pointerInput(selectedDate) {
        awaitEachGesture {
          awaitFirstDown(pass = PointerEventPass.Initial)
          val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
          if (upEvent != null) {
            showModal = true
          }
        }
      }
  )

  if (showModal) {
    DatePickerFieldToModalDialog(
      onDateSelected = {
        selectedDate = it
        onDateSelected(it) // Panggil callback saat tanggal dipilih
      },
      onDismiss = { showModal = false }
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerFieldToModalDialog(
  onDateSelected : (Long) -> Unit,
  onDismiss : () -> Unit
) {
  val datePickerState = rememberDatePickerState()

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Select Date") },
    text = {
      DatePicker(
        state = datePickerState,
        showModeToggle = false
      )
    },
    confirmButton = {
      TextButton(onClick = {
        datePickerState.selectedDateMillis?.let { onDateSelected(it) }
        onDismiss()
      }) {
        Text("OK")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}

fun convertMillisToDate(millis : Long) : String {
  val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
  return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
  onDateSelected : (Long?) -> Unit,
  onDismiss : () -> Unit
) {
  val datePickerState = rememberDatePickerState()

  DatePickerDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(onClick = {
        onDateSelected(datePickerState.selectedDateMillis)
        onDismiss()
      }) {
        Text("OK")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  ) {
    DatePicker(state = datePickerState)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(
  onDateSelected : (Long?) -> Unit,
  onDismiss : () -> Unit
) {
  val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

  DatePickerDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(onClick = {
        onDateSelected(datePickerState.selectedDateMillis)
        onDismiss()
      }) {
        Text("OK")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  ) {
    DatePicker(state = datePickerState)
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
  onDateRangeSelected : (Pair<Long?, Long?>) -> Unit,
  onDismiss : () -> Unit
) {
  val dateRangePickerState = rememberDateRangePickerState()

  DatePickerDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
      TextButton(
        onClick = {
          onDateRangeSelected(
            Pair(
              dateRangePickerState.selectedStartDateMillis,
              dateRangePickerState.selectedEndDateMillis
            )
          )
          onDismiss()
        }
      ) {
        Text("OK")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  ) {
    DateRangePicker(
      state = dateRangePickerState,
      title = {
        Text(
          text = "Select date range"
        )
      },
      showModeToggle = false,
      modifier = Modifier
        .fillMaxWidth()
        .height(500.dp)
        .padding(16.dp)
    )
  }
}

@Composable
fun AllDatePickers() {
  Column(
    modifier = Modifier.padding(16.dp)
  ) {
    Text("Select Date with Docked Picker")
    DatePickerDocked()

    Spacer(modifier = Modifier.height(16.dp))

    Text("Select Date with Modal Picker")
    DatePickerFieldToModal(onDateSelected = { selectedDate ->
      println("Selected Date: ${convertMillisToDate(selectedDate)}")
    })

    Spacer(modifier = Modifier.height(16.dp))

    Text("Select Date with Input Modal")
    var showInputModal by remember { mutableStateOf(false) }
    if (showInputModal) {
      DatePickerModalInput(
        onDateSelected = { selectedDate ->
          selectedDate?.let { println("Input Selected Date: ${convertMillisToDate(it)}") }
        },
        onDismiss = { showInputModal = false }
      )
    }
    Button(onClick = { showInputModal = true }) {
      Text("Show Input Modal DatePicker")
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text("Select Date Range with Modal Picker")
    var showRangeModal by remember { mutableStateOf(false) }
    if (showRangeModal) {
      DateRangePickerModal(
        onDateRangeSelected = { dateRange ->
          val start = dateRange.first?.let { convertMillisToDate(it) } ?: "Not selected"
          val end = dateRange.second?.let { convertMillisToDate(it) } ?: "Not selected"
          println("Selected Date Range: Start - $start, End - $end")
        },
        onDismiss = { showRangeModal = false }
      )
    }
    Button(onClick = { showRangeModal = true }) {
      Text("Show Date Range Picker")
    }
  }
}
