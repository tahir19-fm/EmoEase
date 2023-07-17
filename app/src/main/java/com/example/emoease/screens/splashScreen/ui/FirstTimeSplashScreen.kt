package com.example.emoease.screens.splashScreen.ui

import android.widget.ProgressBar
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.unit.dp


@Composable
fun AppIntroScreen(){
    val progress = remember { mutableStateOf(0f) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
       val x= remember {
           mutableStateOf( ProgressBarRangeInfo(progress.value,0.0f..1.0f,4))
       }
        CustomProgressBar(progress = progress.value, markPoints = listOf(1f,2f,3f))
        Button(onClick = {
        progress.value+=1f
        }) {

        }
    //image
    //intro title
    }
}
@Composable
fun CustomProgressBar(progress: Float, markPoints: List<Float>) {
    val progressBarInfo = ProgressBarRangeInfo(progress, 0.0f..3.0f, 3)

    val progressFraction = (progress - progressBarInfo.range.start) / (progressBarInfo.range.endInclusive - progressBarInfo.range.start)

    Box(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            progress = progressFraction,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Blue
        )
        
        //here you can add anything

        Canvas(modifier = Modifier.fillMaxWidth().padding(top = 1.dp)) {
            drawLine(
                color = Color.Black,
                start = Offset(size.width / 2f, 0f),
                end = Offset(size.width / 2f, size.height),
                strokeWidth = 2f
            )

            markPoints.forEach { markPoint ->
                val markPointFraction = (markPoint - progressBarInfo.range.start) / (progressBarInfo.range.endInclusive - progressBarInfo.range.start)
                val markPointX = markPointFraction * size.width

                drawCircle(
                    color = Color.Red,
                    radius = 12f,
                    center = Offset(markPointX, -0f)
                )
            }
        }
    }
}
