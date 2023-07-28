package com.example.emoease.screens.exerciseScreen.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.R
import com.example.emoease.screens.AnimatedLottieUrl
import com.example.emoease.screens.exerciseScreen.CardYogaExercise
import com.example.emoease.screens.exerciseScreen.util.listOfExercise
import com.example.emoease.utils.FontFamEmo

@Composable
fun ExerciseDescriptionScreen(exerciseIndex: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(text = listOfExercise[exerciseIndex].name)
        val benefits = buildAnnotatedString {
            withStyle(style = SpanStyle(  color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamEmo.quicksand_bold,
                fontSize = 20.sp)) {
                append(stringResource(id = R.string.benifits))
            }
            withStyle(style = SpanStyle()) {
                listOfExercise[exerciseIndex].benefits?.let { append(it.split("...null...")[0]) }
            }
        }
        val howToDo = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamEmo.quicksand_bold,
                    fontSize = 20.sp
                )
            ) {
                append(stringResource(id = R.string.how_to_do))
            }
            withStyle(style = SpanStyle()) {
                listOfExercise[exerciseIndex].benefits?.let { append(it.split("...null...")[1]) }
            }
        }
        LazyColumn (modifier = Modifier.padding(12.dp)){
            item {
                AnimatedLottieUrl(url = listOfExercise[exerciseIndex].icon, modifier = Modifier.wrapContentHeight())
            }
            item {
                CardYogaExercise(modifier = Modifier.wrapContentHeight().padding(0.dp)) {
                    Text(text = benefits, modifier = Modifier.padding(10.dp))
                }

            }
            item {
                CardYogaExercise(modifier = Modifier.wrapContentHeight().padding(0.dp)) {
                    Text(text = howToDo, modifier = Modifier.padding(10.dp))
                }

            }
        }
    }
}