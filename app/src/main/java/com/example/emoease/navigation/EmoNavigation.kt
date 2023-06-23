package com.example.emoease.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.emoease.screens.bottomBar.BottomBarScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodTrackingScreen


@ExperimentalComposeUiApi
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = EmoScreens.SplashScreen.name ){

    }

}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){
        composable(BottomBarScreen.Home.route){
            MoodTrackingScreen(onSaveMood = {},padding=padding)
        }
    }
}