package com.example.basic_01.navigations

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


sealed class AppScreens(val route : String) {
  data object Title : AppScreens("title")
  data object Register : AppScreens("register")
  data object Game : AppScreens("game/{playerName}") {

    fun createRoute(playerName : String) = "game/$playerName"
  }

  data object Match : AppScreens("game/{playerName}/match") {

    fun createRoute(playerName : String) = "game/$playerName/match"
  }

  data object InGame : AppScreens("game/{playerName}/inGame") {

    fun createRoute(playerName : String) = "game/$playerName/inGame"
  }

  data object ResultsWinner : AppScreens("resultsWinner")
  data object GameOver : AppScreens("gameOver")
}

@Composable
fun InitiateNestedNavigationRoute() {
  val navController = rememberNavController()
  NavHost(navController, startDestination = AppScreens.Title.route) {
    composable(route = AppScreens.Title.route) {
      TitleScreen(
        onPlayClicked = { navController.navigate(AppScreens.Register.route) },
        deepLink = "https://facebook.com",
      )
    }
    composable(route = AppScreens.Register.route) {
      RegisterScreen(
        onSignUpComplete = { playerName ->
          navController.navigate(AppScreens.Match.createRoute(playerName))
        }
      )
    }
    navigation(
      startDestination = AppScreens.Match.route,
      route = AppScreens.Game.createRoute("{playerName}")
    ) {
      composable(
        route =
        AppScreens.Match.route,
        arguments = listOf(navArgument("somePlayerName") { type = NavType.StringType })
      ) { backStackEntry ->
        val playerName = backStackEntry.arguments?.getString("somePlayerName") ?: "Guest"
        MatchScreen(
          playerName = playerName,
          onStartGame = { navController.navigate(AppScreens.InGame.createRoute(playerName)) }
        )
      }
      composable(
        route =
        AppScreens.InGame.route,
        arguments = listOf(navArgument("somePlayerName") { type = NavType.StringType })
      ) { backStackEntry ->
        val playerName = backStackEntry.arguments?.getString("somePlayerName") ?: "Guest"
        InGameScreen(
          playerName = playerName,
          onGameWin = { navController.navigate(AppScreens.ResultsWinner.route) },
          onGameLose = { navController.navigate(AppScreens.GameOver.route) }
        )
      }
      composable(route = AppScreens.ResultsWinner.route) {
        ResultsWinnerScreen(
          onNextMatchClicked = {
            /*
              * inclusive = true: Ketika inclusive disetel ke true, layar tujuan dan semua layar di atasnya dalam back stack akan dihapus.
              * popUpTo menentukan layar tujuan yang akan dijadikan titik akhir dalam "penghapusan" layar di back stack. Semua layar sebelum layar tujuan ini akan tetap ada di back stack, sementara semua layar setelah layar tujuan ini akan dihapus.
             */
            navController.navigate(AppScreens.Match.route) {
              popUpTo(AppScreens.Match.route) { inclusive = true }
            }
          },
          onLeaderboardsClicked = { /* Navigate to leaderboards */ }
        )
      }
      composable(route = AppScreens.GameOver.route) {
        GameOverScreen(
          onTryAgainClicked = {
            navController.navigate(AppScreens.Match.route) {
              popUpTo(AppScreens.Match.route) { inclusive = true }
            }
          }
        )
      }
    }
  }
}

@Composable
fun TitleScreen(onPlayClicked : () -> Unit, deepLink : String) {
  val context = LocalContext.current
  Column {
    Text("Title Screen")
    Button(onClick = onPlayClicked) { Text("Play") }
    Button(
      onClick = {
        val intent = Intent(
          Intent.ACTION_VIEW,
          Uri.parse(deepLink)
        )
        context.startActivity(intent)
      }
    ) {

    }
  }
}

@Composable
fun RegisterScreen(onSignUpComplete : (String) -> Unit) {
  Column {
    Text("Register Screen")
    Button(onClick = { onSignUpComplete("PlayerOne") }) {
      Text("Sign Up")
    }
  }
}

@Composable
fun MatchScreen(playerName : String, onStartGame : () -> Unit) {
  Column {
    Text("Match Screen for $playerName")
    Button(onClick = onStartGame) { Text("Start Game") }
  }
}

@Composable
fun InGameScreen(playerName : String, onGameWin : () -> Unit, onGameLose : () -> Unit) {
  Column {
    Text("In Game Screen for $playerName")
    Button(onClick = onGameWin) { Text("Win") }
    Button(onClick = onGameLose) { Text("Lose") }
  }
}

@Composable
fun ResultsWinnerScreen(onNextMatchClicked : () -> Unit, onLeaderboardsClicked : () -> Unit) {
  Column {
    Text("Results Winner Screen")
    Button(onClick = onNextMatchClicked) { Text("Next Match") }
    Button(onClick = onLeaderboardsClicked) { Text("Leaderboards") }
  }
}

@Composable
fun GameOverScreen(onTryAgainClicked : () -> Unit) {
  Column {
    Text("Game Over Screen")
    Button(onClick = onTryAgainClicked) { Text("Try Again") }
  }
}
