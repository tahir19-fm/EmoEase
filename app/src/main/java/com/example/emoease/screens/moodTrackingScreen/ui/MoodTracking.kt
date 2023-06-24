package com.example.emoease.screens.moodTrackingScreen.ui


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.emoease.R
import com.example.emoease.roomDb.EmotionModal
import com.example.emoease.screens.HorizontalSlideAnimation
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun MoodTrackingScreen(
    onSaveMood: (EmotionModal) -> Unit,
    padding: PaddingValues,
    viewModel: MoodTrackingViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    val currMood = remember {
        mutableStateOf(R.raw.happy_emoji)
    }
    val noteText= remember {
        mutableStateOf("")
    }
    val showAlertBox = remember {
        mutableStateOf(false)
    }
    val currentDate = Calendar.getInstance().time
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    val formattedDate = dateFormat.format(currentDate)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
//            .background(Color(0xFFfffaca))
            .padding(paddingValues = padding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MyMood(currMood = currMood.value)
        HorizontalSlideAnimation {
            Rating {
                currMood.value = it
            }
        }
        //get list through view-model
        ActivityCard(
            title = "Activities", icon = Icons.Default.SportsTennis, itemList = listOfActivities
        )
        ActivityCard(title = "Social", icon = Icons.Default.Group, itemList = emptyList())
        ActivityCard(title = "Sleep", icon = Icons.Default.Bed, itemList = listOfActivities)
        ActivityCard(title = "Symptoms", icon = Icons.Default.Face, itemList = listOfActivities)
        MyNotes(showAlertBox,noteText)
        NotesWritingScreen(showDialog = showAlertBox.value,
            onDialogDismiss = { showAlertBox.value = !showAlertBox.value },
            onSaveNote = { showAlertBox.value = !showAlertBox.value },
        noteText=noteText)
        Button(
            onClick = {
                onSaveMood.invoke(
                    EmotionModal(
                        id = formattedDate,
                        mood = currMood.value,
                        activities = emptyList(),
                        social = emptyList(),
                        sleep = emptyList(),
                        symptoms = emptyList(),
                        myNote = noteText.value
                    )
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = "Save")
        }
    }
}


