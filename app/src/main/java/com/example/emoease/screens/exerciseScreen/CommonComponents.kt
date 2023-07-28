package com.example.emoease.screens.exerciseScreen

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.screens.AnimatedLottieUrl
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple
import java.util.Locale.Category

@Composable
fun CardYogaExercise(modifier: Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        content()
    }
}

@Composable
fun CategoryCard(title: String, description: String,onClick:()->Unit) {
    CardYogaExercise(modifier = Modifier.wrapContentHeight()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp).clickableWithoutRipple{
                onClick.invoke()
            }
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamEmo.quicksand_bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colors.primary
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = description,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamEmo.quicksand_regular,
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}