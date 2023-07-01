package com.example.emoease.screens.moodTrackingScreen.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emoease.R
import com.example.emoease.networkService.ApiResult
import com.example.emoease.screens.AnimatedLottie
import com.example.emoease.screens.CustomSnackBar
import com.example.emoease.screens.exerciseScreen.ui.AppHeader
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.moodTrackingScreen.util.listOfMoods
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple
import timber.log.Timber


const val tag = "dsklfhblkjvds"

@Composable
fun MoodHistoryScreen(
    viewModel: MoodTrackingViewModel, navController: NavHostController, padding: PaddingValues
) {
    val loading = remember {
        mutableStateOf(true)
    }
    val showSnackBar = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()
        .padding(padding)) {
        DataObserver(viewModel, loading, showSnackBar)
        HistoryColumn(viewModel, loading,navController)
    }

}

@Composable
private fun HistoryColumn(
    viewModel: MoodTrackingViewModel,
    loading: MutableState<Boolean>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //header(search and sort options)
        AppHeader(text = "History")
        //filterBy
        FilterBy {
            viewModel.getListByMood(it)
        }
        //lazy column
        if (loading.value) {
            AnimatedLottie(animationRes = R.raw.loading_emoji,
                modifier = Modifier.size(100.dp))
        } else {
            val list = viewModel.emotionHistory.value?.data
            if (!list.isNullOrEmpty()) {
                LazyColumn {
                    items(list.reversed()) { item ->
                        EmotionItemCard(animatedRes = item.mood, date = item.id){
                            //TODO navigate to details screen
                        }
                    }
                }
            }
        }
        //item card date and mood
        //on click details of that mood
//        AnimatedLottie(animationRes = R.raw.very_sad_
    }
}

@Composable
private fun DataObserver(
    viewModel: MoodTrackingViewModel,
    loading: MutableState<Boolean>,
    showSnackBar: MutableState<Boolean>
) {
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.getEmotionHistory()
    }
    when (val d = viewModel.emotionHistory.observeAsState().value) {
        ApiResult.Loading -> {
            //show loading screen
            loading.value = true
        }

        is ApiResult.Success -> {
            loading.value = false
            Timber.tag(tag).d(d.data.toString())
            Toast.makeText(context, d.data.toString(), Toast.LENGTH_LONG).show()
        }

        is ApiResult.Error -> {
            //show error
            loading.value = false
            Timber.tag(tag).e(d.message)
            showSnackBar.value = true
            CustomSnackBar(text = d.message.toString(), showSnackBar = showSnackBar)
        }

        else -> {

        }
    }
}

@Composable
fun EmotionItemCard(animatedRes: Int, date: String,onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickableWithoutRipple {
                  onClick.invoke(date)
            },
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp),
                shape = CircleShape,
                elevation = 2.dp
            ) {
                AnimatedLottie(animationRes = animatedRes, modifier = Modifier.size(160.dp))
            }
            Text(
                text = date,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                style = TextStyle(
                    fontFamily = FontFamEmo.quicksand_semi_bold,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

    }
}

@Composable
fun FilterBy(onClick: (Int) -> Unit) {
    val list = listOfMoods
    val expanded = remember {
        mutableStateOf(false)
    }
    val selectedItem = remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(30.dp), backgroundColor = MaterialTheme.colors.surface
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(6.dp)
            ) {
                Icon(imageVector = Icons.Default.FilterAlt, contentDescription = null)
                Text(text = "Filter by")
            }
        }
        Icon(imageVector = if (expanded.value) Icons.Default.Close else Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier.clickable {
                expanded.value = !expanded.value
            })
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (expanded.value) items(list) { item ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .clickableWithoutRipple {
                            selectedItem.value = item.mood
                            onClick.invoke(item.drawableRes)
                        },
                    backgroundColor = if (selectedItem.value == item.mood)
                        MaterialTheme.colors.secondary
                    else MaterialTheme.colors.surface
                ) {
                    Text(text = item.mood, modifier = Modifier.padding(6.dp))
                }
            }

        }
    }
}