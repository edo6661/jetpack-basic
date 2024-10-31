package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawers() {
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val scope = rememberCoroutineScope()

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet(
        drawerShape = RoundedCornerShape(
          bottomEnd = 16.dp,
          topEnd = 16.dp
        ),
        drawerContainerColor = Color.Blue,
        drawerContentColor = Color.Green,

        ) {
        Column() {
          // Drawer Header
          Text(
            text = "Menu",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
          )
          HorizontalDivider(
            modifier = Modifier.padding(bottom = 16.dp)
          )

          // Drawer items
          NavigationDrawerItem(
            icon = { Icon(Icons.Filled.Add, contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {
              scope.launch { drawerState.close() }
            }
          )

          NavigationDrawerItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {
              scope.launch { drawerState.close() }
            }
          )

          NavigationDrawerItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = false,
            onClick = {
              scope.launch { drawerState.close() }
            }
          )
        }
      }
    },
    gesturesEnabled = drawerState.isOpen,
    scrimColor = Color.Red,

    ) {
    Scaffold(
      floatingActionButton = {
        ExtendedFloatingActionButton(
          text = { Text("Show drawer") },
          icon = { Icon(Icons.Filled.Add, contentDescription = "") },
          onClick = {
            scope.launch {
              drawerState.apply {
                if (isClosed) open() else close()
              }
            }
          }
        )
      }
    ) { contentPadding ->
      // Screen content here
      Text(
        text = "Main Content",
        modifier = Modifier.padding(contentPadding)
      )
    }
  }
}
