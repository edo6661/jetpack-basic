package com.example.basic_01.ui.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SliderMinimalExample() {
  var sliderPosition by remember { mutableFloatStateOf(0f) }
  Column() {
    Slider(
      value = sliderPosition,
      onValueChange = { sliderPosition = it }
    )
    Text(text = sliderPosition.toString())
  }
}


@Composable
fun SliderAdvancedExample() {
  var sliderPosition by remember { mutableFloatStateOf(0f) }
  Column {
    Slider(
      value = sliderPosition,
      onValueChange = { sliderPosition = it },
      colors = SliderDefaults.colors(
        thumbColor = MaterialTheme.colorScheme.secondary,
        activeTrackColor = MaterialTheme.colorScheme.secondary,
        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
      ),
      steps = 3,
      valueRange = 0f..50f
    )
    Text(text = sliderPosition.toString())
  }
}

@Composable
fun RangeSliderExample() {
  var sliderPosition by remember { mutableStateOf(0f..100f) }
  Column {
    RangeSlider(
      value = sliderPosition,
      steps = 5,
      onValueChange = { range -> sliderPosition = range },
      valueRange = 0f..100f,
      onValueChangeFinished = {
        // viewModel.updateSelectedSliderValue(sliderPosition)
      },
    )
    Text(text = sliderPosition.toString())
  }
}

@Composable
fun sliderRating(
  maxRating : Int = 5,
  rating : Int = 0,
  onRatingChange : (Int) -> Unit
) {
  var currentRating by remember { mutableStateOf(rating) }

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    for (i in 1..maxRating) {
      IconButton(
        onClick = {
          currentRating = i
          onRatingChange(currentRating)
        }
      ) {
        Icon(
          imageVector = if (i <= currentRating) Icons.Filled.Star else Icons.Filled.Close,
          contentDescription = "Rating Star $i",
          tint = if (i <= currentRating) Color.Yellow else Color.Gray,
          modifier = Modifier.size(32.dp)
        )
      }
    }
  }
  Text(text = currentRating.toString())
}

@Composable
fun RatingExample() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    var rating by remember { mutableStateOf(0) }
    sliderRating(
      rating = rating,
      onRatingChange = { newRating ->
        rating = newRating
      }
    )
  }
}

@Composable
fun AllSliders() {
  Column {
    SliderMinimalExample()
    SliderAdvancedExample()
    RangeSliderExample()
    RatingExample()
  }
}