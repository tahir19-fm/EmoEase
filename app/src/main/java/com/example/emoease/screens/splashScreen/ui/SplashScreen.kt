package com.example.emoease.screens.splashScreen.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.emoease.R
import com.example.emoease.navigation.Graph
import com.example.emoease.navigation.SplashScreens
import com.example.emoease.screens.StatusBarColor
import com.example.emoease.screens.accountScreen.util.AccountViewModel
import com.example.emoease.screens.splashScreen.util.SplashViewModel
import com.example.emoease.utils.FontFamEmo
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController, viewModel: SplashViewModel
) {
    StatusBarColor(color = MaterialTheme.colors.surface)
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(viewModel) {
        scale.animateTo(
            targetValue = 0.9f, animationSpec = tween(durationMillis = 1200,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        viewModel.exists()
        delay(2000)
        if (viewModel.exist.value == true) {
            navController.navigate(Graph.Bottom) {
                navController.graph.route?.let {
                    popUpTo(it) {
                        inclusive = true
                    }
                }
            }
        } else {
            navController.navigate(SplashScreens.IntroScreen.route) {
                navController.graph.route?.let {
                    popUpTo(it) {
                        inclusive = true
                    }
                }
            }
        }

    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .size(330.dp)
                    .scale(scale.value),
                shape = CircleShape,
                color = MaterialTheme.colors.surface,
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            ) {
                Column(
                    modifier = Modifier.padding(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = TextStyle(
                            fontFamily = FontFamEmo.logo,
                            fontSize = 84.sp,
                            color = MaterialTheme.colors.primary
                        ),
                    )

                }

            }
        }

    }
}