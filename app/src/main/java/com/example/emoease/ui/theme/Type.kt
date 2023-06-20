package com.example.emoease.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.emoease.utils.FontFamEmo

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamEmo.quicksand_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    ,
    button = TextStyle(
        fontFamily = FontFamEmo.quicksand_semi_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamEmo.quicksand_light,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )

)