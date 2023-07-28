package com.example.emoease.screens.exerciseScreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emoease.screens.ImageFromURL
import com.example.emoease.screens.exerciseScreen.CardYogaExercise
import com.example.emoease.screens.exerciseScreen.modal.Poses
import com.example.emoease.screens.exerciseScreen.util.ExerciseViewModel
import com.example.emoease.utils.FontFamEmo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun YogaCategoryItemsScreen(viewModel: ExerciseViewModel, navController: NavController) {
    LazyColumn {
        stickyHeader {
            viewModel.categoryItems.value?.categoryName?.let { AppHeader(text = it) }
        }
        viewModel.categoryItems.value?.let {
            items(it.poses) { item ->
                YogaPosesCard(item = item)
            }
        }
    }
}

@Composable
fun YogaPosesCard(item: Poses) {
    CardYogaExercise(modifier = Modifier.wrapContentHeight()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            val benifits = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Benifits : ")
                }
                withStyle(style = SpanStyle()) {
                    item.poseBenefits?.let { append(it) }
                }
            }
            val description = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Description : ")
                }
                withStyle(style = SpanStyle()) {
                    item.poseDescription?.let { append(it) }
                }
            }
            item.englishName?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        color = MaterialTheme.colors.primary,
                        fontSize = 26.sp,
                        fontFamily = FontFamEmo.quicksand_bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            item.urlPng?.let { ImageFromURL(modifier = Modifier.wrapContentSize(), url = it) }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = benifits)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = description)

        }
    }
}