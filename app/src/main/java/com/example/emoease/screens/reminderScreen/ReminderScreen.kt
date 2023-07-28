package com.example.emoease.screens.reminderScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.screens.AnimatedLottieUrl
import com.example.emoease.screens.exerciseScreen.ui.AppHeader
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.LottieUrls

@Composable
fun ReminderScreen() {
    Column(modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        AppHeader(text = "Reminder")
        ItemReminder()
        Text(text = "Coming Soon..."
            , style = TextStyle(fontSize = 42.sp, fontFamily = FontFamEmo.quicksand_bold)
        )
    }
}

@Composable
fun ItemReminder() {
    val cardData = listOf(
        CardData("Medicine", LottieUrls.MEDICINE),
        CardData("Water", LottieUrls.WATER),
        CardData("Work", LottieUrls.WORK),
        CardData("Others", LottieUrls.OTHERS),
    )

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items = cardData.chunked(2)) { index, rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowItems.forEach { card ->
                    ReminderCard(card, modifier = Modifier.weight(1f)) {
//                        NavigateTo()
                    }
                }
            }
        }
    }
}

@Composable
private fun ReminderCard(card: CardData, modifier: Modifier, onClick: (String) -> Unit) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .clickable {
                onClick.invoke(card.title)
            }, backgroundColor = MaterialTheme.colors.surface, elevation = 4.dp
    ) {
        Column(modifier=Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedLottieUrl(url = card.icon, modifier = Modifier.size(200.dp))
            Text(text = card.title, color = MaterialTheme.colors.onSurface,
            style = TextStyle(fontWeight = FontWeight.Bold, fontFamily = FontFamEmo.quicksand_bold,
            fontSize = 24.sp)
            )
        }

    }
}

data class CardData(val title: String, val icon: String)
