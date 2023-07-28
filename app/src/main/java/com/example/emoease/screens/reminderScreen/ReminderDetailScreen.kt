package com.example.emoease.screens.reminderScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.emoease.screens.exerciseScreen.ui.AppHeader

@Composable
fun ReminderDetailScreen(title:String){
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(text = "$title Reminders", showIcon = true){
            setReminder()
        }
        ReminderContent()
    }
}

fun setReminder() {

}

@Composable
fun ReminderContent() {
    val list= emptyList<String>()
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(list){

        }
    }
}
