package com.example.emoease.navigation

import java.lang.IllegalArgumentException

object Graph {
    const val Root = "root_graph"
    const val Authentication = "auth_graph"
    const val Mood = "mood_graph"
    const val Account = "account_graph"
    const val Bottom = "bottom_graph"
    const val Splash = "splash_graph"
    const val Exercise = "exercise_graph"
}

sealed class MoodScreens(val route: String) {
    object InitMood : MoodScreens("init_mood_screen")
    object ActivitySelectScreen : MoodScreens("activity_select_screen")
    object HistoryMoodDetailScreen : MoodScreens("history_mood_detail_screen")

}

sealed class AuthScreens(val route: String) {
    object LoginScreen : AuthScreens("login_screen")
}

sealed class SplashScreens(val route: String) {
    object SplashScreen : SplashScreens("splash_screen")
    object IntroScreen : SplashScreens("intro_screen")
}

sealed class ExerciseScreens(val route: String) {
    object ExerciseDetailScreen : ExerciseScreens("exercise_detail_screen")
    object YogaExercisesScreen : ExerciseScreens("yoga_exercises_screen")
    object YogaExercisesCategoryItemsScreen :
        ExerciseScreens("yoga_exercises_category_items_screen")
}