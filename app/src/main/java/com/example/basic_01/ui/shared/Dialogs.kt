package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.basic_01.R

@Composable
fun AlertDialogExample(
  onDismissRequest : () -> Unit,
  onConfirmation : () -> Unit,
  title : String,
  text : String,
  icon : ImageVector
) {

  AlertDialog(
    icon = {
      Icon(icon, contentDescription = "Example Icon")
    },

    title = {
      Text(title)
    },
    text = {
      Text(text)
    },
    onDismissRequest = {
      onDismissRequest()
    },
    confirmButton = {
      TextButton(
        onClick = {
          onConfirmation()
        }
      ) {
        Text("Confirm")
      }
    },
    shape = RoundedCornerShape(16.dp),
    modifier = Modifier.background(Color.Yellow),
    titleContentColor = Color.Red,
    properties = DialogProperties(
      dismissOnBackPress = true,
      dismissOnClickOutside = true
    ),
    tonalElevation = 36.dp,
    iconContentColor = Color.Blue,
    containerColor = Color.Green,
    textContentColor = Color.Black,
    dismissButton = {
      TextButton(
        onClick = {
          onDismissRequest()
        }
      ) {
        Text("Dismiss")
      }
    }


  )
}

@Composable
fun AlertDialogImageExample(
  onDismissRequest : () -> Unit,
  onConfirmation : () -> Unit,
  painter : Painter,
  imageDescription : String,
) {
  Dialog(onDismissRequest = { onDismissRequest() }) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(375.dp)
        .padding(16.dp)
        .background(
          color = Color.Red
        ),

      shape = RoundedCornerShape(16.dp),
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .background(
            color = Color.Green
          ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Image(
          painter = painter,
          contentDescription = imageDescription,
          contentScale = ContentScale.Fit,
          modifier = Modifier
            .height(160.dp)
        )
        Text(
          text = "This is dialog with image",
          modifier = Modifier
            .padding(16.dp)
        )
        Row(
          modifier = Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          TextButton(
            onClick = { onDismissRequest() },
            modifier = Modifier.padding(8.dp),
          ) {
            Text("Dismiss")
          }
          TextButton(
            onClick = { onConfirmation() },
            modifier = Modifier.padding(8.dp),
          ) {
            Text("Confirm")
          }
        }
      }
    }
  }
}

@Composable
fun DialogExample() {
  var showDialog1 by remember { mutableStateOf(false) }
  var showDialog2 by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(32.dp)
  ) {
    Button(onClick = {
      showDialog1 = true
    }) {
      Text("Show Dialog 1")
    }
    Button(onClick = {
      showDialog2 = true
    }) {
      Text("Show Dialog 2")
    }

  }

  if (showDialog1) AlertDialogExample(
    onDismissRequest = { showDialog1 = false },
    onConfirmation = {
      Log.d("Dialog", "Dialog 1 confirmed")
    },
    title = "Title 1",
    icon = Icons.Filled.Info,
    text = "title 1 text"
  )
  if (showDialog2) AlertDialogImageExample(
    onDismissRequest = { showDialog2 = false },
    onConfirmation = {
      Log.d("Dialog", "Dialog 2 confirmed")
    },
    painter = painterResource(id = R.drawable.ic_launcher_background),
    imageDescription = "Image Description"
  )

}