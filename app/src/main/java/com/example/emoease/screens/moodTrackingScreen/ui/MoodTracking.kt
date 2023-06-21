package com.example.emoease.screens.moodTrackingScreen.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.emoease.R
import com.example.emoease.screens.moodTrackingScreen.data.MoodEntry
import com.example.emoease.screens.moodTrackingScreen.data.MoodItem

@Composable
fun MoodTrackingScreen(onSaveMood: (MoodEntry) -> Unit) {
    val rating = remember { mutableStateOf(1f) }
    val notes = remember { mutableStateOf("") }
    val tags = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Rate your mood:")

        Rating(rating)

        Text(text = "Notes:")
        TextField(
            value = notes.value,
            onValueChange = { value -> notes.value = value },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Tags:")
        TextField(
            value = tags.value,
            onValueChange = { value -> tags.value = value },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val moodEntry = MoodEntry(
                    rating = rating.value.toInt(),
                    notes = notes.value,
                    tags = tags.value.split(",").map { it.trim() },
                    timestamp = System.currentTimeMillis()
                )
                onSaveMood(moodEntry)
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
private fun Rating(rating: MutableState<Float>) {
    val listOfMoods= listOf(MoodItem(0,R.drawable.happy_login_img),
        MoodItem(1,R.drawable.happy_login_img),
        MoodItem(2,R.drawable.happy_login_img),
        MoodItem(3,R.drawable.happy_login_img),
        MoodItem(4,R.drawable.happy_login_img),
        MoodItem(5,R.drawable.happy_login_img)
    )
    Column {
        MoodRow(moods = listOfMoods)

    }

}

@Composable
fun MoodRow(
    moods: List<MoodItem>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues( vertical = 8.dp)
    ) {
        items(moods) { mood ->
            CircleCard(
                modifier = Modifier.padding(horizontal = 8.dp),
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ) {
                Image(
                    painter = painterResource(id = mood.drawableRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(12.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun CircleCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 2.dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.clip(MaterialTheme.shapes.small).padding(2.dp),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = CircleShape
    ) {
        content()
    }
}
