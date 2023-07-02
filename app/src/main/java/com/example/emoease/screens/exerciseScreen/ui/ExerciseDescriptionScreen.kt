package com.example.emoease.screens.exerciseScreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.emoease.screens.AnimatedLottieUrl
import com.example.emoease.screens.exerciseScreen.util.Exercise
import com.example.emoease.screens.exerciseScreen.util.listOfExercise

@Composable
fun ExerciseDescriptionScreen (exerciseIndex: Int){
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(text = listOfExercise[exerciseIndex].name)
        LazyColumn {
            item {
                AnimatedLottieUrl(url = listOfExercise[exerciseIndex].icon)
            }
            item {
                listOfExercise[exerciseIndex].description?.let { Text(text = it) }
            }
        }
    }
}