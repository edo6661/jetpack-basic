package com.example.basic_01.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable

fun BoxText(
  modifier: Modifier = Modifier,
  value: String = "Default Value",
  fontSize: TextUnit = 24.sp,
  fontWeight : FontWeight = FontWeight.Normal,
  color : Color = Color.Black,
  fontStyle : FontStyle = FontStyle.Normal,
  maxLines : Int = Int.MAX_VALUE,
  overflow : TextOverflow = TextOverflow.Ellipsis,
) {
  Box(
    modifier = Modifier
      .background(Color.Red)
      // w-full
      .fillMaxWidth()
      // justify-center
      .wrapContentWidth(Alignment.CenterHorizontally)
      .padding(24.dp)
    ) {
    Text(
      modifier = Modifier
        .background(Color.Cyan)
        .border(
          width = 4.dp,
          color = Color.Black,
          // border-radius
          shape = RoundedCornerShape(8.dp)
        )
        // opacity-50
//        .alpha(0.5f)
        .padding(24.dp)
      ,
      text = value,
      fontSize = fontSize,
      fontWeight = FontWeight.Bold,
      color = Color.Red,
      fontStyle = FontStyle.Italic,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,

      )
  }
}