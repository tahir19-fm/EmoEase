package com.example.emoease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.emoease.screens.bottomBar.BottomBarScreenContent
import com.example.emoease.screens.bottomBar.MainScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodTrackingScreen
import com.example.emoease.ui.theme.EmoEaseTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmoEaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()

                //   MoodTrackingScreen(onSaveMood = {}, padding = padding)
                 //   LoginScreen()
                }
            }
        }
    }
}

