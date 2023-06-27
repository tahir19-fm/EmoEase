package com.example.emoease.screens.moodTrackingScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.networkService.ApiResult
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple
import timber.log.Timber

@Composable
fun ActivityItemScreen(
    paddingValues: PaddingValues, viewModel: MoodTrackingViewModel, title: String?
) {
    LaunchedEffect(viewModel) {
        viewModel.getActivityItems(title ?: "")
    }
    val loading = remember {
        mutableStateOf(true)
    }
    val list: MutableList<String> = ArrayList()
    val selectedItems = remember(list) {
        mutableStateOf(list)
    }
    val showDialogue = remember {
        mutableStateOf(false)
    }
    val text = remember {
        mutableStateOf("")
    }
    LaunchedEffect(viewModel) {
        when (viewModel.selectedItems.value) {
            ApiResult.Loading -> {
                //loading
                loading.value = true
            }

            is ApiResult.Success -> {
                loading.value = false
            }

            is ApiResult.Error -> {

            }

            else -> {}
        }
    }

    if (!loading.value) Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {


        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            val listOfItems = viewModel.activityItems.observeAsState().value?.data?.items
            val selected = viewModel.selectedItems.value?.data
            if (selected != null) {
                selectedItems.value.addAll(selected)
            }
            AlertBox(showDialog = showDialogue.value,
                onDialogDismiss = {
                                  //do nothing
                                  showDialogue.value=false
                }, onSaveNote = {
                    saveData(listOfItems, title, viewModel, it)
                    showDialogue.value=false
            }, noteText = text)
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (listOfItems != null) {
                    items(listOfItems.chunked(2)) { rowItems ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            rowItems.forEach { item ->
                                // Display your item composable here
                                Timber.tag("seleleel").d(viewModel.checkSelected(item).toString())

                                val selected = remember {
                                    mutableStateOf(viewModel.checkSelected(item))
                                }
                                ItemCard(itemName = item, selected = selected.value) {
                                    selected.value = !selected.value
                                    Timber.tag("getitemName").d("${selectedItems.value} $it")

                                    if (selectedItems.value.contains(it)) {
                                        selectedItems.value.remove(it)
                                    } else {
                                        selectedItems.value.add(it)
                                    }
                                }
                            }
                        }
                    }
                }else{
                    item{
                        Text(text = "")
                    }

                }
                item {
                    Card(
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier.padding(12.dp),
                        backgroundColor = MaterialTheme.colors.secondary
                    ) {
                        Text(text = "Add more",
                            modifier = Modifier
                                .clickableWithoutRipple {
                                    showDialogue.value = true
                                }
                                .padding(6.dp),
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ))
                    }

                }
            }
            Button(
                onClick = {
                    Timber.tag("selectedValiue").d(title.toString())
                    viewModel.selectedItems(list = selectedItems.value, title ?: "")

                },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .height(60.dp)
                    .width(180.dp)
            ) {
                Text(
                    text = "Done",
                    style = TextStyle(fontSize = 24.sp, fontFamily = FontFamEmo.quicksand_semi_bold)
                )
            }
        }
    }
}

private fun saveData(
    listOfItems: List<String>?,
    title: String?,
    viewModel: MoodTrackingViewModel,
    it: String
) {
    if (listOfItems.isNullOrEmpty()) {
        if (title != null) {
            if (title.trim().isNotEmpty() && title.trim().isNotBlank()) {
                viewModel.updateActivityItem(id = title, newItem = it)
            }
        }
    } else {
        if (!listOfItems.contains(it)) {
            if (title != null) {
                if (title.trim().isNotEmpty() && title.trim().isNotBlank()) {
                    viewModel.updateActivityItem(id = title, newItem = it)
                }
            }
        }

    }
}