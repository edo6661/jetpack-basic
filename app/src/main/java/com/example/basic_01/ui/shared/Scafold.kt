package com.example.basic_01.ui.shared

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scafold(content : @Composable (PaddingValues) -> Unit = {}) {
  Scaffold(
    topBar = {
      // bisa pakai TopAppBar defaultnya / bikin sendiri
//      TopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//          containerColor = Color.Red,
//          titleContentColor = Color.Blue,
//        ),
//        title = {
//          Text(
//            text = "Title"
//          )
//        }
//      )
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .background(
            color = Color.Green,
            shape = RectangleShape
          )
      ) {
        Text(
          text = "Top Bar",
          modifier = Modifier.align(Alignment.Center)
        )
      }
    },
    bottomBar = {
      BottomAppBar(
        containerColor = Color.Red,
        contentColor = Color.Blue
      ) {
        Text(
          text = "Bottom Bar"
        )
      }
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          Log.d("Scafold", "FloatingActionButton Clicked")
        }
      ) {
        Icon(
          Icons.Default.Add, contentDescription = "Add"
        )
      }
    },
    floatingActionButtonPosition = FabPosition.End,

    ) { innerPadding ->
    content(innerPadding)
  }
}

@Composable
@Preview(showSystemUi = true)
fun DynamicContentScafold() {
  Scafold(content = { innerPadding ->
    Column(
      modifier = Modifier.padding(innerPadding),

      ) {
      for (i in 0..10) {
        Text(
          text = "Item $i",
        )
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(
              top = 16.dp
            )
            .height(1.dp)
            .background(
              color = Color.Red,
              shape = RectangleShape
            )
        )
        Spacer(
          modifier = Modifier.height(8.dp)
        )
      }
    }
  })
}