package com.example.emoease.screens.exerciseScreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.emoease.R
import com.example.emoease.navigation.ExerciseScreens
import com.example.emoease.networkService.ApiResult
import com.example.emoease.screens.AnimatedLottie
import com.example.emoease.screens.CustomSnackBar
import com.example.emoease.screens.exerciseScreen.CategoryCard
import com.example.emoease.screens.exerciseScreen.modal.YogaCategories
import com.example.emoease.screens.exerciseScreen.util.ExerciseViewModel
import com.example.emoease.utils.isNetworkAvailable
import timber.log.Timber

@Composable
fun YogaExercisesScreen(viewModel: ExerciseViewModel, navController: NavController) {
    val retry = remember {
        mutableStateOf(false)
    }
    when (val it = viewModel.categories.observeAsState().value) {
        ApiResult.Loading -> {
            AnimatedLottie(animationRes = R.raw.loading, modifier = Modifier.fillMaxSize())
        }

        is ApiResult.Success -> {
            Timber.tag("Data").d(it.data.toString())
            CategoriesScreen(data = it.data as List<YogaCategories>) { items ->
                viewModel.setCategoryItems(items).run {
                    navController.navigate(ExerciseScreens.YogaExercisesCategoryItemsScreen.route)
                }
            }
        }

        is ApiResult.Error -> {
            val showSnackBar = remember {
                mutableStateOf(true)
            }
            it.message?.let { it1 -> CustomSnackBar(text = it1, showSnackBar = showSnackBar) }
        }

        else -> {}
    }
        if (isNetworkAvailable(context = LocalContext.current)) {
            LaunchedEffect(viewModel) {
                viewModel.getActivityItems()
                retry.value = true
            }
    } else {
        val showSnackBar = remember {
            mutableStateOf(true)
        }
        AnimatedLottie(animationRes = R.raw.no_network, modifier = Modifier.fillMaxSize())
        CustomSnackBar(
            text = stringResource(id = R.string.no_internet),
            showSnackBar = showSnackBar,
            delayTime = 200000
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(data: List<YogaCategories>, onClick: (YogaCategories) -> Unit) {
    LazyColumn {
        stickyHeader {
            AppHeader(text = "Yoga Categories")
        }
        items(data) { item ->
            item.categoryName?.let {
                item.categoryDescription?.let { it1 ->
                    CategoryCard(
                        title = it,
                        description = it1
                    ) {
                        onClick.invoke(item)
                    }
                }
            }
        }
    }
}
