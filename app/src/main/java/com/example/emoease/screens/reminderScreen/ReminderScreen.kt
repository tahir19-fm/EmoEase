package com.example.emoease.screens.reminderScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.emoease.screens.exerciseScreen.ui.AppHeader

@Composable
fun ReminderScreen(){
    Column(modifier = Modifier.fillMaxWidth()) {
        AppHeader(text = "Reminder")
        ItemReminder()
    }
}

@Composable
fun ItemReminder() {
    val cardData = listOf(
        CardData("Card 1", Color.Red),
        CardData("Card 2", Color.Blue),
        CardData("Card 3", Color.Green),
        CardData("Card 4", Color.Yellow),
        CardData("Card 5", Color.Magenta),
        CardData("Card 6", Color.Cyan)
    )

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = cardData.chunked(2)) { index, rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowItems.forEach { card ->
                    ReminderCard(card, modifier = Modifier.weight(1f)){
//                        NavigateTo()
                    }
                }
            }
        }
    }
}

@Composable
private fun ReminderCard(card: CardData,modifier: Modifier,onClick:(String)->Unit) {
    Card(
        modifier=modifier
            .height(200.dp)
            .clickable{
                      onClick.invoke(card.title)
            },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Text(
            text = card.title,
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}

data class CardData(val title: String, val color: Color)
