package com.example.emoease.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.emoease.R
import com.example.emoease.utils.FontFamEmo

@Composable
fun LogoApp(modifier: Modifier = Modifier, fontSize: TextUnit=80.sp) {
    Text(
        text = stringResource(id = R.string.app_name),
        style = TextStyle(
            color = MaterialTheme.colors.secondary,
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamEmo.logo
        ),
        modifier = modifier
    )
}