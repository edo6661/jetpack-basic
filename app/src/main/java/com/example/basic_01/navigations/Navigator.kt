package com.example.basic_01.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.basic_01.screens.HomeScreen
import com.example.basic_01.screens.ProfileScreen
import com.example.basic_01.ui.dialogs.SomeDialog

sealed class Screen(val route : String) {
  data object Home : Screen("home")
  data object Profile : Screen("profile/{name}") {

    fun createRoute(name : String) = "profile/$name"
  }
}

@Composable
fun InitiateNavigateRoutes() {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "home") {
    composable(Screen.Home.route) {
      HomeScreen(navController = navController)
    }
    composable(
      route = Screen.Profile.route,
      arguments = listOf(navArgument("name") {
        defaultValue = "defaultName"
        type = NavType.StringType
      })
    ) { backStackEntry ->
      val name = backStackEntry.arguments?.getString("name") ?: "defaultName"
      ProfileScreen(
        name
      )

    }

    dialog("someDialog") {
      SomeDialog()
    }
  }
}