package com.example.emoease.screens.exerciseScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.screens.AnimatedLottieUrl
import com.example.emoease.screens.exerciseScreen.util.listOfExercise
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple

@Composable
fun ExerciseScreen() {
    Column {
        AppHeader()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(listOfExercise) { rowItems ->

                ExerciseItemCard(
                    name = rowItems.name, url = rowItems.icon
                )

            }
        }
    }
}

@Composable
fun AppHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Exercises",
                style = TextStyle(
                    fontSize = 34.sp,
                    fontFamily = FontFamEmo.quicksand_bold,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun ExerciseItemCard(name: String, url: String) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(0.6f)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .clickableWithoutRipple {

                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .padding(12.dp),
                shape = CircleShape,
                elevation = 2.dp
            ) {
                AnimatedLottieUrl(url = url, modifier = Modifier.size(160.dp))
            }
            Text(
                text = name, modifier = Modifier.padding(12.dp), style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamEmo.quicksand_bold
                )
            )
        }
    }
}