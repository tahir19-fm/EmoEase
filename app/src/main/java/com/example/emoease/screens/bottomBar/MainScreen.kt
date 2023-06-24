package com.example.emoease.screens.bottomBar

 import android.annotation.SuppressLint
 import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
 import androidx.compose.foundation.ExperimentalFoundationApi
 import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.text.TextStyle
 import androidx.compose.ui.unit.sp
 import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
 import androidx.navigation.compose.rememberNavController
 import com.example.emoease.navigation.BottomNavGraph
 import com.example.emoease.utils.FontFamEmo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()){
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        BottomBarScreen.Home.route->{
            bottomBarState.value=true
        }
        BottomBarScreen.History.route->{
            bottomBarState.value=true
        }
        BottomBarScreen.Account.route->{
            bottomBarState.value=true
        }
        else->{
            bottomBarState.value=false
        }
    }

   Scaffold( bottomBar = {
        BottomBar(
            navController = navController,
            bottomBarState=bottomBarState
        )
    }) {

           BottomNavGraph(navController = navController
               ,padding=it)
    }

}

@Composable
fun BottomBar(navController: NavHostController, bottomBarState: MutableState<Boolean>){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.History,
        BottomBarScreen.Account
    )


    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
          BottomBarScreenContent(navController = navController, bottomBarState = bottomBarState,screens=screens)
        })
    }




private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}

@Composable
fun BottomBarScreenContent(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    screens: List<BottomBarScreen>
){

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        BottomNavigation(
        ) {
            // this is a row scope
            // all items are added horizontally
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            screens.forEach { menuItem ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == menuItem.route } == true

                // adding each item
                BottomNavigationItem(
                    selected = (selected),
                    onClick = {
                        if (selected) return@BottomNavigationItem
                        navController.navigate(menuItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painterResource(id = if (selected) menuItem.iconFilled else menuItem.iconOutlined),
                            contentDescription = menuItem.route
                        )
                    },
                    label = {
                        Text(text = menuItem.title, style = TextStyle(fontFamily =if(selected) FontFamEmo.quicksand_medium else FontFamEmo.quicksand_regular, fontSize = 14.sp))
                    },
                    enabled = true
                )
            }
        }
    }
}