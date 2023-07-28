package com.example.emoease.screens.moodTrackingScreen.ui

import android.content.ClipData.Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.screens.CardContent
import com.example.emoease.screens.exerciseScreen.ui.AppHeader
import com.example.emoease.screens.moodTrackingScreen.util.Constants
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.utils.FontFamEmo

@Composable
fun MoodDetailScreen(viewModel: MoodTrackingViewModel) {
    val data = viewModel.selectedItemHistory.observeAsState().value
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
//        viewModel.selectedItemHistory.value?.let { AppHeader(text = it.id) }
        if (data != null) {
            MyMood(currMood = data.mood, false, data.id)
        }
        if (data != null) {
            DetailsItemCard(
                title = Constants.Activities,
                icon = Icons.Default.SportsTennis,
                list = data.activities
            )
            DetailsItemCard(title = Constants.Social, icon = Icons.Default.Group, data.social)
            DetailsItemCard(title = Constants.Sleep, icon = Icons.Default.Bed, data.sleep)
            DetailsItemCard(title = Constants.Symptoms, icon = Icons.Default.Face, data.symptoms)

            CardContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = "My Notes",
                        style = TextStyle(
                            color = MaterialTheme.colors.primary,
                            fontSize = 34.sp,
                            fontFamily = FontFamEmo.quicksand_bold
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = data.myNote.ifEmpty { "Nothing to show" },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 14.dp),
                        style = TextStyle(
                            fontSize = 22.sp, fontFamily = FontFamEmo.quicksand_semi_bold,

                        )
                    )
                }
            }
        }
    }

}

@Composable
fun DetailsItemCard(title: String, icon: ImageVector, list: List<String>? = null) {
//    CardContent(
//        modifier = Modifier
//            .wrapContentHeight()
//            .padding(horizontal = 4.dp)
//    ) {
    if (list != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            ActivitiesName(title = title, icon = icon)
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                items(list) {
                    ItemCard(itemName = it)
                }
            }
        }
    }
//    }


}