package com.example.emoease.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.emoease.screens.accountScreen.ui.AccountProfileScreen
import com.example.emoease.screens.authScreen.ui.LoginScreen
import com.example.emoease.screens.moodTrackingScreen.util.MoodTrackingViewModel
import com.example.emoease.screens.bottomBar.BottomBarScreen
import com.example.emoease.screens.bottomBar.MainScreen
import com.example.emoease.screens.exerciseScreen.ui.ExerciseDescriptionScreen
import com.example.emoease.screens.exerciseScreen.ui.ExerciseScreen
import com.example.emoease.screens.exerciseScreen.ui.YogaCategoryItemsScreen
import com.example.emoease.screens.exerciseScreen.ui.YogaExercisesScreen
import com.example.emoease.screens.exerciseScreen.util.ExerciseViewModel
import com.example.emoease.screens.moodTrackingScreen.ui.ActivityItemScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodDetailScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodHistoryScreen
import com.example.emoease.screens.moodTrackingScreen.ui.MoodTrackingScreen
import com.example.emoease.screens.reminderScreen.ReminderScreen
import com.example.emoease.screens.splashScreen.ui.AppIntroScreen
import com.example.emoease.screens.splashScreen.ui.SplashScreen
import com.example.emoease.screens.splashScreen.util.SplashViewModel


@Composable
fun RootNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.Root,
        startDestination = Graph.Splash
    ) {
        splashNavGraph(navController)
        authNavGraph(navController)
        composable(Graph.Bottom) {
            MainScreen()
        }
    }
}

fun NavGraphBuilder.splashNavGraph(navController: NavHostController) {
    navigation(route = Graph.Splash, startDestination = SplashScreens.SplashScreen.route) {
        composable(SplashScreens.SplashScreen.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen(navController = navController, viewModel = viewModel)
        }
        composable(SplashScreens.IntroScreen.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            AppIntroScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
) {
    val viewModel: MoodTrackingViewModel = hiltViewModel()
    val exerciseViewModel: ExerciseViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        route = Graph.Bottom,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(BottomBarScreen.Home.route) {
            MoodTrackingScreen(onSaveMood = {
                viewModel.saveMood(it)
            }, padding = padding, viewModel = viewModel, navController = navController)

        }
        composable(BottomBarScreen.Account.route) {
            AccountProfileScreen(navController = navController, padding = padding)
        }
        composable(BottomBarScreen.History.route) {
            MoodHistoryScreen(
                viewModel = viewModel,
                navController = navController,
                padding = padding
            )
        }
        composable(BottomBarScreen.Exercise.route) {
            ExerciseScreen(navController,padding)
        }
        composable(BottomBarScreen.Reminder.route) {
            ReminderScreen()
        }
        moodNavGraph(navController, padding, viewModel)
        exerciseNavGraph(navController, exerciseViewModel)


    }
}

fun NavGraphBuilder.exerciseNavGraph(
    navController: NavHostController,
    exerciseViewModel: ExerciseViewModel
) {

    navigation(
        route = Graph.Exercise,
        startDestination = ExerciseScreens.ExerciseDetailScreen.route
    ) {
        composable(ExerciseScreens.ExerciseDetailScreen.route + "/{index}", arguments = listOf(
            navArgument(name = "index") {
                type = NavType.IntType
            }
        )) { navBack ->
            navBack.arguments?.getInt("index").let { index ->
                if (index != null) {
                    ExerciseDescriptionScreen(exerciseIndex = index)
                }
            }
        }
        composable(ExerciseScreens.YogaExercisesScreen.route) {
            YogaExercisesScreen(exerciseViewModel, navController)
        }
        composable(ExerciseScreens.YogaExercisesCategoryItemsScreen.route) {
            YogaCategoryItemsScreen(viewModel = exerciseViewModel, navController = navController)
        }
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(route = Graph.Authentication, startDestination = AuthScreens.LoginScreen.route) {
        composable(AuthScreens.LoginScreen.route) {
            LoginScreen()
        }
    }
}

fun NavGraphBuilder.moodNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: MoodTrackingViewModel
) {
    navigation(route = Graph.Mood, startDestination = MoodScreens.InitMood.route) {
        composable(MoodScreens.InitMood.route) {
        }
        val route = MoodScreens.ActivitySelectScreen.route
        composable("$route/{title}", arguments = listOf(
            navArgument(name = "title") {
                type = NavType.StringType
            }
        )) { navBack ->
            navBack.arguments?.getString("title").let { title ->
                ActivityItemScreen(
                    paddingValues = padding,
                    viewModel = viewModel,
                    title = title,
                    navController = navController
                )
            }
        }
        composable(MoodScreens.HistoryMoodDetailScreen.route){
            MoodDetailScreen(viewModel = viewModel)
        }
    }
}