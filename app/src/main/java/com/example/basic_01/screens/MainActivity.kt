package com.example.basic_01.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import coil3.ImageLoader
import coil3.request.crossfade
import com.example.basic_01.navigations.InitiateNavigateRoutes


class MainActivity : ComponentActivity() {


  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)
    ImageLoader.Builder(this)
      .crossfade(true)
      .build()

    setContent {
      InitiateNavigateRoutes()

    }

  }
}

