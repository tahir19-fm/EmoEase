package com.example.emoease.screens.exerciseScreen.util

import com.example.emoease.EmoEaseApp
import com.example.emoease.R
import com.example.emoease.utils.StringUtil

data class Exercise(
    val name: String,
    val icon: String,
    val benefits: String? = null,
    val howTo: String? = null
)

val listOfExercise = listOf(
    Exercise(
        "Yoga",
        "https://assets10.lottiefiles.com/packages/lf20_1vtrvkam.json"
    ),
    Exercise(
        "Walking", "https://assets5.lottiefiles.com/packages/lf20_1dez0eja.json",
        StringUtil.walking
    ),
    Exercise(
        "Meditation",
        "https://assets3.lottiefiles.com/packages/lf20_Yiahbq.json",
        StringUtil.meditation
    ),
    Exercise(
        "Dance",
        "https://assets10.lottiefiles.com/private_files/lf30_rjqwaenm.json",
        StringUtil.dance
    ),
    Exercise(
        "Running",
        "https://assets7.lottiefiles.com/packages/lf20_3ueg3po6.json",
        StringUtil.running
    ),
    Exercise(
        "Hiking",
        "https://assets9.lottiefiles.com/packages/lf20_veqkjqvf.json",
        StringUtil.hiking
    ),
    Exercise(
        "Swimming",
        "https://assets3.lottiefiles.com/packages/lf20_pjaextie.json",
        StringUtil.swimming
    ),
    Exercise(
        "Aerobic exercise",
        "https://assets6.lottiefiles.com/packages/lf20_cfrvib6d.json",
        StringUtil.aerobicExercises
    ),
    Exercise(
        "Cycling",
        "https://assets8.lottiefiles.com/packages/lf20_qwJp5gPCld.json",
        StringUtil.cycling
    ),
    Exercise(
        "Diaphragmatic breathing",
        "https://assets7.lottiefiles.com/private_files/lf30_i2pyppik.json",
        StringUtil.diaphragmaticBreathing
    ),
    Exercise(
        "Strength training", "https://assets5.lottiefiles.com/packages/lf20_JkRdsa6Exx.json",
        StringUtil.strengthTraining
    ),
    Exercise(
        "Stretching", "https://assets3.lottiefiles.com/packages/lf20_VBz0xZZjd6.json",
        StringUtil.streching
    ),
    Exercise(
        "Boxing", "https://assets3.lottiefiles.com/packages/lf20_z85kfusv.json",
        StringUtil.boxing
    )
)
