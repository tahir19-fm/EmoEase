package com.example.emoease.screens.moodTrackingScreen.ui


import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emoease.R
import com.example.emoease.navigation.MoodScreens
import com.example.emoease.networkService.ApiResult
import com.example.emoease.roomDb.EmotionModal
import com.example.emoease.screens.AnimatedLottie
import com.example.emoease.screens.HorizontalSlideAnimation
import com.example.emoease.screens.moodTrackingScreen.util.Constants
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.moodTrackingScreen.util.todayDate
import com.example.emoease.utils.FontFamEmo

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
    val dbNoteValue= remember {
        mutableStateOf("")
    }
    val showAlertBox = remember {
        mutableStateOf(false)
    }
    //TODO: if the date is not in db add new option to start insertion of new date into db

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
        viewModel.checkIfExists()
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
                dbNoteValue.value=data.myNote
                symptoms = data.symptoms
            }
            loading.value = false

        }

        is ApiResult.Error -> {

        }

        else -> {}
    }
    when(val it=viewModel.exists.observeAsState().value){
        ApiResult.Loading->{
            AnimatedLottie(animationRes = R.raw.loading)
        }
        is ApiResult.Success->{
            if (it.data == false) SetNewMoodDetails {
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
            }
            else {
                SetMoodContent(
                    scrollState,
                    padding,
                    currMood,
                    viewModel,
                    formattedDate,
                    activities,
                    navController,
                    social,
                    sleep,
                    symptoms,
                    showAlertBox,
                    noteText,
                    dbNoteValue
                )
            }
        }

        else -> {

        }
    }

}

@Composable
fun SetNewMoodDetails(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No data for today",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamEmo.quicksand_bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = {
            onClick.invoke()
        }) {
            Text(text = "Create",  style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamEmo.quicksand_bold,
                fontSize = 28.sp
            ) )
        }
    }
}

@Composable
private fun SetMoodContent(
    scrollState: ScrollState,
    padding: PaddingValues,
    currMood: MutableState<Int>,
    viewModel: MoodTrackingViewModel,
    formattedDate: String,
    activities: List<String>,
    navController: NavHostController,
    social: List<String>,
    sleep: List<String>,
    symptoms: List<String>,
    showAlertBox: MutableState<Boolean>,
    noteText: MutableState<String>,
    dbNoteValue: MutableState<String>
) {
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
            viewModel.selectedItems(activities, Constants.Activities)
            navController.navigate(MoodScreens.ActivitySelectScreen.route + "/$it")
        }
        ActivityCard(
            title = Constants.Social, icon = Icons.Default.Group, itemList = social
        ) {
            viewModel.selectedItems(social, Constants.Social)
            navController.navigate(MoodScreens.ActivitySelectScreen.route + "/$it")
        }
        ActivityCard(
            title = Constants.Sleep, icon = Icons.Default.Bed, itemList = sleep
        ) {
            viewModel.selectedItems(sleep, Constants.Sleep)
            navController.navigate(MoodScreens.ActivitySelectScreen.route + "/$it")
        }
        ActivityCard(
            title = Constants.Symptoms, icon = Icons.Default.Face, itemList = symptoms
        ) {
            viewModel.selectedItems(symptoms, Constants.Symptoms)
            navController.navigate(MoodScreens.ActivitySelectScreen.route + "/$it")
        }
        MyNotes(showAlertBox, noteText)

        AlertBox(
            showDialog = showAlertBox.value,
            onDialogDismiss = {
                showAlertBox.value = !showAlertBox.value
                noteText.value=dbNoteValue.value
                              },
            onSaveNote = { showAlertBox.value = !showAlertBox.value
                viewModel.updateNotes(it, formattedDate)
                dbNoteValue.value=it
                         },
            noteText = noteText
        )
    }
}




