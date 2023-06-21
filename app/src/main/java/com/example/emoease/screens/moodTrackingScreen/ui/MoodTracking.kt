package com.example.emoease.screens.moodTrackingScreen.ui


import android.content.ClipData.Item
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.R
import com.example.emoease.screens.moodTrackingScreen.data.MoodEntry
import com.example.emoease.screens.moodTrackingScreen.data.MoodItem
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import com.example.emoease.ui.theme.Purple200
import com.example.emoease.utils.FontFamEmo
import kotlinx.coroutines.delay

@Composable
fun MoodTrackingScreen(onSaveMood: (MoodEntry) -> Unit) {
    val rating = remember { mutableStateOf(1f) }
    val notes = remember { mutableStateOf("") }
    val tags = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val currMood = remember {
        mutableStateOf(R.drawable.happy)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFfffaca)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MyMood(currMood = currMood.value)
        Rating(rating) {
            currMood.value = it
        }
        //get list through viewmodel
        ActivityCard(
            title = "Activities", icon = Icons.Default.SportsTennis, itemList = listOfActivities
        )
        ActivityCard(title = "Social", icon = Icons.Default.Group, itemList = listOfActivities)
        ActivityCard(title = "Sleep", icon = Icons.Default.Bed, itemList = listOfActivities)
        ActivityCard(title = "Symptoms", icon =Icons.Default.Face, itemList = listOfActivities)
//        Text(text = "Notes:")
//        TextField(
//            value = notes.value,
//            onValueChange = { value -> notes.value = value },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Text(text = "Tags:")
//        TextField(
//            value = tags.value,
//            onValueChange = { value -> tags.value = value },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Button(
//            onClick = {
//                val moodEntry = MoodEntry(
//                    rating = rating.value.toInt(),
//                    notes = notes.value,
//                    tags = tags.value.split(",").map { it.trim() },
//                    timestamp = System.currentTimeMillis()
//                )
//                onSaveMood(moodEntry)
//            }, modifier = Modifier.align(Alignment.End)
//        ) {
//            Text(text = "Save")
//        }
    }
}

@Composable
fun MyMood(currMood: Int) {
    val isVisible = remember { mutableStateOf(false) }
    LaunchedEffect(currMood) {
        isVisible.value = false
        delay(500)
        isVisible.value = true
    }
    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .background(MaterialTheme.colors.primary), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = currMood),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )


        }
        //write mood note
        Text(text = "")
    }


}

@Composable
private fun Rating(rating: MutableState<Float>, onClick: (Int) -> Unit) {
    val listOfMoods = listOf(
        MoodItem("happy", R.drawable.happy),
        MoodItem("very happy", R.drawable.very_happy),
        MoodItem("neutral", R.drawable.neutral),
        MoodItem("sad", R.drawable.sad),
        MoodItem("sad and tired", R.drawable.sad_tired),
        MoodItem("angry", R.drawable.angry_head),
        MoodItem("very angry", R.drawable.very_angry),
    )
    Column {
        Text(
            text = "Rate your mood:",
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
            style = TextStyle(fontFamily = FontFamEmo.quicksand_semi_bold, fontSize = 20.sp)
        )
        MoodRow(moods = listOfMoods) {
            onClick.invoke(it)
        }
    }

}

@Composable
fun MoodRow(
    moods: List<MoodItem>, modifier: Modifier = Modifier, onClick: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier
    ) {
        items(moods) { mood ->
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    onClick.invoke(mood.drawableRes)
                }) {
                CircleCard(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.White
                ) {
                    Image(
                        painter = painterResource(id = mood.drawableRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(6.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Text(text = mood.mood)
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
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .padding(2.dp),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = CircleShape
    ) {
        content()
    }
}

@Composable
fun ActivityCard(title: String, icon: ImageVector, itemList: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.wrapContentSize()) {
            Text(
                text = title, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamEmo.quicksand_semi_bold,
                    fontSize = 20.sp
                )
            )
            Icon(imageVector = icon, contentDescription = null)
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(modifier = Modifier.wrapContentSize()) {
            items(itemList) { name ->
                ItemCard(name)
            }
            item{
                Text(text = "Change")
            }
        }
    }
}

@Composable
fun ItemCard(itemName: String) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 4.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                listOf(
                    MaterialTheme.colors.primary, MaterialTheme.colors.secondary
                )
            )
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = itemName,
            style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onSurface),
            modifier = Modifier.padding(10.dp)
        )
    }
}


