package com.example.emoease.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emoease.screens.bottomBar.BottomBarScreen
import com.example.emoease.screens.exerciseScreen.ui.ExerciseScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodHistoryScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodTrackingScreen
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){
        composable(BottomBarScreen.Home.route){
            val viewModel:MoodTrackingViewModel = hiltViewModel()
            MoodTrackingScreen(onSaveMood = {
                                            viewModel.saveMood(it)
            },padding=padding,viewModel=viewModel,navController=navController)
        }
        composable(BottomBarScreen.Account.route){
        }
        composable(BottomBarScreen.History.route){
            MoodHistoryScreen()
        }
        composable(BottomBarScreen.Exercise.route){
            ExerciseScreen()
        }

    }
}