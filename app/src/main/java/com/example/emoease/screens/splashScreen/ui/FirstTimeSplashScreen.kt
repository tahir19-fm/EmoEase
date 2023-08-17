package com.example.emoease.screens.splashScreen.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emoease.R
import com.example.emoease.navigation.Graph
import com.example.emoease.screens.StatusBarColor
import com.example.emoease.screens.splashScreen.util.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppIntroScreen(navController: NavController,viewModel: SplashViewModel) {
    StatusBarColor(color = MaterialTheme.colors.background)
    Column(modifier = Modifier.fillMaxSize()) {
        val tabs = listOf("", "", "")
        val state = rememberPagerState()
        IndicatorTab(tabs = tabs, state = state)
        PagerScreens(tabs = tabs, state = state){
            viewModel.initIstTime()
            viewModel.initActivity()
            navController.navigate(Graph.Bottom) {
                navController.graph.route?.let {
                    popUpTo(it) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScreens(tabs: List<String>, state: PagerState,onClick:()->Unit={}) {
    HorizontalPager(
        state = state, count = tabs.size
    ) { page ->
        val scope= rememberCoroutineScope()
        when (page) {
            0 -> {
                IntroScreenContent(
                    title = "Record Your Daily Mood",
                    stringContent = listOf(
                        "Instant Notes",
                        "Save Mood With Ratings",
                        "Record Your Daily progress"
                    ),
                    icon = R.drawable.intro_screen_emoji,
                    buttonTitle = "Next",
                    onClickSkip = onClick
                ) {
                    scope.launch {
                        state.animateScrollToPage(1)
                    }
                }
            }

            1 -> {
                IntroScreenContent(
                    title = "Track your mood history",
                    stringContent = listOf(
                        "Records saved in device",
                        "Get filtered mood records",
                        "Interact and get details"
                    ),
                    icon = R.drawable.history_svg,
                    buttonTitle = "Next",
                    onClickSkip = onClick
                ) {
                    scope.launch {
                        state.animateScrollToPage(2)
                    }
                }
            }

            2 -> {
                IntroScreenContent(
                    title = "Set Reminders",
                    stringContent = listOf(
                        "Get notified",
                        "Set reminders with categories",
                        "Get notified about your medicine"
                    ),
                    icon = R.drawable.reminder_svg,
                    buttonTitle = "Done",
                    onClickSkip = onClick
                ) {
                    onClick.invoke()
                }

            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun IndicatorTab(tabs: List<String>, state: PagerState) {
    var selected by remember(state.currentPage) { mutableStateOf(state.currentPage) }
    val size by remember { mutableStateOf(Size.Zero) }
    val width: @Composable () -> Dp = { with(LocalDensity.current) { size.width.toDp() } }
    val scope = rememberCoroutineScope()
    val tabColors = List(tabs.size) { index ->
        if (index < selected) Color.Green else Color.Black
    }
    val tabColorsPics = List(tabs.size) { index ->
        if (index <= selected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    }
    val tabPics= listOf(R.drawable.one_svg,R.drawable.two_svg,R.drawable.three_svg)
    TabRow(selectedTabIndex = selected,
        backgroundColor = MaterialTheme.colors.background,
        indicator = {
            TabRowDefaults.Indicator(
                modifier = Modifier.farmerTabIndicatorOffset(width(), it[selected]),
                color = Color.Red,
                height = 3.dp
            )
        },
        divider = {}) {
        tabs.forEachIndexed { index, tab ->
            Tab(selected = selected == index, onClick = {
                selected = index
                scope.launch { state.scrollToPage(index) }
            }, icon = {
                Icon(painter = painterResource(id = tabPics[index]), contentDescription = null, tint = tabColorsPics[index])
            },
                selectedContentColor = Color.Red,
                unselectedContentColor = tabColors[index]
            )
        }
    }
}


fun Modifier.farmerTabIndicatorOffset(
    width: Dp, currentTabPosition: TabPosition
): Modifier = composed(inspectorInfo = debugInspectorInfo {
    name = "farmerTabIndicatorOffset"
    value = currentTabPosition
}) {
    val currentTabWidth by animateDpAsState(
        targetValue = width,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
    )

    val padding = (currentTabPosition.width - width) / 2

    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = padding + indicatorOffset)
        .width(currentTabWidth)
}