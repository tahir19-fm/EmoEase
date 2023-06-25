package com.example.emoease.screens.moodTrackingScreen.ui


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.emoease.R
import com.example.emoease.navigation.MoodScreens
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.EmotionModal
import com.example.emoease.screens.HorizontalSlideAnimation
import com.example.emoease.screens.moodTrackingScreen.util.Constants
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import com.example.emoease.screens.moodTrackingScreen.util.listOfSleep
import com.example.emoease.screens.moodTrackingScreen.util.listOfSocial
import com.example.emoease.screens.moodTrackingScreen.util.todayDate
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
    val loading = remember {
        mutableStateOf(true)
    }
    val scrollState = rememberScrollState()
    val currMood = remember {
        mutableStateOf(R.raw.happy_emoji)
    }
    var activities = listOf("")
    var social = listOf("")

    var sleep = listOf("")
    var symptoms = listOf("")

    val noteText = remember {
        mutableStateOf("")
    }
    val showAlertBox = remember {
        mutableStateOf(false)
    }
    //observing mood data
    when (val value = viewModel.mood.observeAsState().value) {
        ApiResult.Loading -> {

        }

        is ApiResult.Success -> {
            if (value.data != null) {
                currMood.value = value.data
            }
        }

        else -> {}
    }
    val formattedDate = todayDate()
    //making call to collect data from db
    LaunchedEffect(viewModel) {
        viewModel.getMood(formattedDate)
        viewModel.getEmotionDetails(formattedDate)
    }
    //observing mood details
    when (val it = viewModel.emotionDetails.observeAsState().value) {
        ApiResult.Loading -> {
            loading.value = true
            //
        }

        is ApiResult.Success -> {
            if (it.data != null) {
                val data = it.data
                noteText.value = data.myNote
                activities = data.activities
                social = data.social
                sleep = data.sleep
                symptoms = data.symptoms
            }
            loading.value = false

        }

        is ApiResult.Error -> {

        }

        else -> {}
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
//            .background(Color(0xFFfffaca))
            .padding(paddingValues = padding), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MyMood(currMood = currMood.value)
        HorizontalSlideAnimation {
            Rating {
                currMood.value = it
                viewModel.updateMood(it, formattedDate)

            }
        }
        //get list through view-model
        ActivityCard(
            title = Constants.Activities, icon = Icons.Default.SportsTennis, itemList = activities
        ) {
            viewModel.selectedItems(activities,Constants.Activities)
            navController.navigate(MoodScreens.ActivitySelectScreen.route + "/$it")
        }
        ActivityCard(
            title = Constants.Social, icon = Icons.Default.Group, itemList = social
        ) {

        }
        ActivityCard(
            title = Constants.Sleep, icon = Icons.Default.Bed, itemList = sleep
        ) {

        }
        ActivityCard(
            title = Constants.Symptoms, icon = Icons.Default.Face, itemList = symptoms
        ) {

        }
        MyNotes(showAlertBox, noteText)
        AlertBox(
            showDialog = showAlertBox.value,
            onDialogDismiss = { showAlertBox.value = !showAlertBox.value },
            onSaveNote = { showAlertBox.value = !showAlertBox.value },
            noteText = noteText
        )
        Button(
            onClick = {
                onSaveMood.invoke(
                    EmotionModal(
                        id = formattedDate,
                        mood = currMood.value,
                        activities = listOfActivities,
                        social = listOfSocial,
                        sleep = listOfSleep,
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




