package com.example.emoease.screens.splashScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emoease.utils.FontFamEmo

@Composable
fun IntroScreenContent(
    title: String,
    stringContent: List<String?>,
    buttonTitle: String,
    icon: Int,
    onClickSkip: () -> Unit={},
    onClick: () -> Unit = {}
) {
    val state= rememberScrollState()

    val color1 = MaterialTheme.colors.primary
    val color2 = MaterialTheme.colors.secondary
    val color3 = MaterialTheme.colors.onSurface
    val annotatedStringContent = buildAnnotatedString {
        withStyle(SpanStyle(color = color1)) {
            append(stringContent[0] ?: "")
        }
        withStyle(SpanStyle(color = color2)) {
            append(("\n" + stringContent[1]))
        }
        withStyle(SpanStyle(color = color3)) {
            append(("\n" + stringContent[2]))
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(state),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 46.sp, color = MaterialTheme.colors.primary,
                fontFamily = FontFamEmo.quicksand_bold, fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = annotatedStringContent,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(fontFamily = FontFamEmo.quicksand_bold, fontSize = 34.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(70.dp)
        ) {
            Text(
                text = buttonTitle,
                style = TextStyle(fontSize = 38.sp, fontFamily = FontFamEmo.quicksand_bold)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "skip",
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline,
                fontSize = 18.sp
            ),
            modifier = Modifier.clickable {
                onClickSkip.invoke()
            }
        )
    }
}