package com.example.emoease.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.R
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple
import kotlinx.coroutines.delay

@Composable
fun LogoApp(modifier: Modifier = Modifier, fontSize: TextUnit = 80.sp) {
    Text(
        text = stringResource(id = R.string.app_name), style = TextStyle(
            color = MaterialTheme.colors.onSecondary,
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamEmo.logo
        ), modifier = modifier
    )
}

@Composable
fun DrawLineSolid(
    modifier: Modifier = Modifier,
    strokeWidth: Float = 2f,
    color: Color = Color.Black.copy(alpha = 0.7f)
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width + 1, 0f),
            strokeWidth = strokeWidth
        )
    }
}

@Composable
fun CustomSnackBar(
    text: String,
    clickLabel: String = "Click",
    isLabelActive: Boolean = false,
    onClick: () -> Unit = {},
    showSnackBar: MutableState<Boolean>
) {
    if (!showSnackBar.value) return
    val isVisible = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(true) {
        delay(2000)
        isVisible.value = false
        delay(200)
        showSnackBar.value = false
    }

    Column(
        verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxHeight()
    ) {
        AnimatedVisibility(visible = isVisible.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(2.dp)
                    .clickableWithoutRipple { }
                    .background(Color.Red.copy(alpha = 0.8f)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    if (isLabelActive) {
                        Text(text = clickLabel,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clickableWithoutRipple {
                                    onClick.invoke()
                                })

                    }
                }
            })
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HorizontalSlideAnimation( content: @Composable () -> Unit ){
    val visibility = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(visibility){
        delay(1)
        visibility.value=true
    }
    val slowEasing = CubicBezierEasing(0.2f, 0.0f, 0.2f, 1.0f)
    AnimatedVisibility(
        visible = visibility.value,
        enter = slideInHorizontally(
            initialOffsetX = { 300 },
            animationSpec = tween(durationMillis = 1500, easing = slowEasing)
        )){
            content()
        }
}

