package com.example.emoease.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.emoease.R
import com.example.emoease.utils.FontFamEmo
import com.example.emoease.utils.clickableWithoutRipple
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun LogoApp(modifier: Modifier = Modifier, fontSize: TextUnit = 80.sp) {
    Text(
        text = stringResource(id = R.string.app_name), style = TextStyle(
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
fun AppLogo(modifier: Modifier, tint: Color) {
    Icon(
        painter = painterResource(id = R.drawable.ic_app_logo),
        contentDescription = "appLogo", modifier = modifier,
        tint=tint
    )
}

@Composable
fun CustomSnackBar(
    text: String,
    clickLabel: String = "Click",
    isLabelActive: Boolean = false,
    onClick: () -> Unit = {},
    showSnackBar: MutableState<Boolean>,
    delayTime: Long = 2000
) {
    if (!showSnackBar.value) return
    val isVisible = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(true) {
        delay(delayTime)
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

@Composable
fun HorizontalSlideAnimation(content: @Composable () -> Unit) {
    val visibility = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(visibility) {
        delay(1)
        visibility.value = true
    }
    val slowEasing = CubicBezierEasing(0.2f, 0.0f, 0.2f, 1.0f)
    AnimatedVisibility(
        visible = visibility.value,
        enter = slideInHorizontally(
            initialOffsetX = { 300 },
            animationSpec = tween(durationMillis = 1500, easing = slowEasing)
        )
    ) {
        content()
    }
}

@Composable
fun AnimatedLottie(
    animationRes: Int,
    modifier: Modifier = Modifier
) {
    val isPlaying = remember {
        mutableStateOf(true)
    }
    val speed = remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(animationRes)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying.value,
        speed = speed.value,
        restartOnPlay = true

    )
    LottieAnimation(
        composition,
        progress,
        modifier = modifier.size(400.dp)
    )
}

@Composable
fun AnimatedLottieUrl(url: String, modifier: Modifier = Modifier) {
    val isPlaying = remember {
        mutableStateOf(true)
    }
    val speed = remember {
        mutableStateOf(1f)
    }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url(url)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying.value,
        speed = speed.value,
        restartOnPlay = true

    )
    LottieAnimation(
        composition,
        progress,
        modifier = modifier.size(400.dp)
    )
}

@Composable
fun StatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = color
    )
}

@Composable
fun ImageFromURL(modifier: Modifier, url: String, contentDescription: String? = null) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )

}

@Composable
fun CardContent(modifier: Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        content()
    }
}

@Composable
fun LoadingOverlay(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    val isClickable = remember { mutableStateOf(!isLoading) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = isClickable.value) {
            /* Handle click if not loading */ }
    ) {
        // Add content if not loading
        if (!isLoading) {
            content()
        }

        // Loading indicator
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = 0.5f)
            ) {
                // You can use any loading indicator UI here
                // For example, a circular progress indicator
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Center),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}
