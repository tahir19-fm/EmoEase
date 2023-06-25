package com.example.emoease.screens.moodTrackingScreen.ui

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.moodTrackingScreen.util.listOfActivities
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple

@Composable
fun ActivityItemScreen(
    paddingValues: PaddingValues,
    viewModel: MoodTrackingViewModel,
    title: String?
){
    LaunchedEffect(viewModel){
        viewModel.getActivityItems(title?:"Activities")
    }
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
    ) {
       val list: MutableList<String> = ArrayList()
        val selectedItems= remember(list) {
            mutableStateOf(list)
        }
        val showDialogue= remember {
            mutableStateOf(false)
        }
        val text = remember {
            mutableStateOf("")
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            var listOfItems= viewModel.activityItems.value?.data?.items
            if (listOfItems == null) {
                listOfItems= listOfActivities
            }
            AlertBox(showDialog = showDialogue.value, onDialogDismiss = { /*TODO*/ }, onSaveNote ={

            } , noteText = text)
            LazyColumn(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                    items(  listOfItems.chunked(2)) { rowItems ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                            horizontalArrangement = Arrangement.Center) {
                            rowItems.forEach { item ->
                                // Display your item composable here
                                val selected= remember {
                                    mutableStateOf(false)
                                }
                                ItemCard(itemName = item, selected = selected.value){
                                    selected.value=!selected.value
                                    if (selectedItems.value.contains(it)){
                                        selectedItems.value.remove(it)
                                    }else{
                                        selectedItems.value.add(it)
                                    }
                                }
                            }
                        }
                    }
                item {
                    Text(text = "add more", modifier = Modifier.clickableWithoutRipple {

                    })
                }
            }
            Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                modifier = Modifier
                    .height(60.dp)
                    .width(180.dp)
            ) {
                Text(text = "Done", style = TextStyle(fontSize = 24.sp, fontFamily = FontFamEmo.quicksand_semi_bold))
            }
        }
    }
}