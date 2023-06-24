package com.example.emoease.screens.moodTrackingScreen.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.contentColorFor
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.R
import com.example.emoease.screens.AnimatedLottie
import com.example.emoease.screens.HorizontalSlideAnimation
import com.example.emoease.screens.moodTrackingScreen.data.MoodItem
import com.example.emoease.utils.FontFamEmo
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun MyMood(currMood: Int) {
    val isVisible = remember { mutableStateOf(false) }
    LaunchedEffect(currMood) {
        isVisible.value = false
        delay(500)
        isVisible.value = true
    }
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    val dayFormat= SimpleDateFormat("EEEE", Locale.getDefault())
    val formattedDay=dayFormat.format(currentDate)
    val formattedDate = dateFormat.format(currentDate)
    Column(  modifier = Modifier
        .height(200.dp)
        .clip(shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
        .background(MaterialTheme.colors.primary),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = formattedDay, fontFamily = FontFamEmo.quicksand_bold, fontWeight = FontWeight.Bold, fontSize = 44.sp, color = MaterialTheme.colors.background)
        Text(text = formattedDate, fontSize = 20.sp, color = MaterialTheme.colors.background)
        Box(
            modifier = Modifier
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
//            Image(
//                painter = painterResource(id = currMood),
//                contentDescription = null,
//                modifier = Modifier.size(100.dp)
//            )
            AnimatedLottie(animationRes = currMood, modifier = Modifier.size(100.dp))

        }
        //write mood note
        Text(text = "")
    }


}

@Composable
fun Rating( onClick: (Int) -> Unit) {
    val listOfMoods = listOf(
        MoodItem("happy", R.raw.happy_emoji),
        MoodItem("very happy", R.raw.very_happy_emojji),
        MoodItem("neutral", R.raw.neutral_emoji),
        MoodItem("sad", R.raw.sad_emoji),
        MoodItem("sad and tired", R.raw.very_sad_emoji),
        MoodItem("angry", R.raw.angry_emoji),
        MoodItem("very angry", R.raw.very_angry_emoji),
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
//                    Image(
//                        painter = painterResource(id = mood.drawableRes),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(80.dp)
//                            .padding(6.dp),
//                        contentScale = ContentScale.Fit
//                    )
                    AnimatedLottie(animationRes = mood.drawableRes,modifier=Modifier.size(80.dp))

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
        HorizontalSlideAnimation {
            LazyRow(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (itemList.isEmpty()) {
                    item {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = MaterialTheme.colors.error
                        ) {
                            Text(
                                text = "No $title selected",
                                style = MaterialTheme.typography.caption,
                                color = MaterialTheme.colors.onError,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(4.dp)
                            )
                        }

                    }
                }
                items(itemList) { name ->
                    ItemCard(name)
                }
                item {
                    Text(text = "Change", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamEmo.quicksand_bold,
                        fontSize = 18.sp
                    ), modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            //TODO on click change we will open a new screen
                        })
                }
            }
        }
    }
}

@Composable
fun ItemCard(itemName: String,modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
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
            style = TextStyle(fontSize = 18.sp),
            modifier = modifier.padding(10.dp)
        )
    }
}

@Composable
fun MyNotes(showAlertBox: MutableState<Boolean>, noteText: MutableState<String>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Note",
            style = TextStyle(fontSize = 24.sp, fontFamily = FontFamEmo.quicksand_bold),
            modifier = Modifier.padding(8.dp)
        )
        ItemCard(itemName = noteText.value.ifEmpty {
            stringResource(id = R.string.click_to_add_Note)
        }, modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            showAlertBox.value = !showAlertBox.value
        }) {
            Text(text = "Edit Note")
        }
    }
}


@Composable
fun NotesWritingScreen(
    showDialog: Boolean,
    onDialogDismiss: () -> Unit,
    onSaveNote: (String) -> Unit,
    noteText: MutableState<String>
) {
    if (showDialog) {
        AlertDialog(onDismissRequest = { onDialogDismiss() },
            confirmButton = {
                Button(onClick = { onSaveNote.invoke(noteText.value)
                    onDialogDismiss()}) {
                    Text(text = "Save")
                }
            }, dismissButton = {
                Button(onClick = {
                    onDialogDismiss()
                }) {
                    Text(text = "Cancel")
                }
            }, text = {
                Column {
                    Text(
                        text = "Write a Note",
                        modifier = Modifier.padding(6.dp),
                        color = Color.Black
                    )
                    TextField(
                        value = noteText.value,
                        onValueChange = {noteText.value=it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 8.dp),
                        textStyle = MaterialTheme.typography.body1,

                        )
                }

            })
    }
}

