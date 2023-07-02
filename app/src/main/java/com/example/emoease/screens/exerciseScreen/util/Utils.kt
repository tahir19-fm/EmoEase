package com.example.emoease.screens.exerciseScreen.util

import android.graphics.drawable.Icon
import com.example.emoease.R

data class Exercise(val name: String, val icon: String, val description: String? = null)

val listOfExercise = listOf(
    Exercise(
        "Yoga",
        "https://assets10.lottiefiles.com/packages/lf20_1vtrvkam.json",
        "Meditation is a great way to help calm the mind and body and " +
                "get you out of the fight or flight zone. Meditation also works proactively" +
                " by helping you learn to stay calm during stressful situations. It doesn’t " +
                "have to be complicated: listen to a guided meditation, set your gaze on a single " +
                "point of focus (like a candle) and try to only think about the flame, or do a few " +
                "minutes of deep diaphragmatic breathing."
    ),
    Exercise("Walking", "https://assets5.lottiefiles.com/packages/lf20_1dez0eja.json"),
    Exercise("Meditation", "https://assets3.lottiefiles.com/packages/lf20_Yiahbq.json"),
    Exercise("Dance", "https://assets10.lottiefiles.com/private_files/lf30_rjqwaenm.json"),
    Exercise("Running", "https://assets7.lottiefiles.com/packages/lf20_3ueg3po6.json"),
    Exercise("Hiking", "https://assets9.lottiefiles.com/packages/lf20_veqkjqvf.json"),
    Exercise("Swimming", "https://assets3.lottiefiles.com/packages/lf20_pjaextie.json"),
    Exercise("Aerobic exercise", "https://assets6.lottiefiles.com/packages/lf20_cfrvib6d.json"),
    Exercise("Cycling", "https://assets8.lottiefiles.com/packages/lf20_qwJp5gPCld.json"),
    Exercise(
        "Diaphragmatic breathing",
        "https://assets7.lottiefiles.com/private_files/lf30_i2pyppik.json"
    ),
    Exercise("Strength training", "https://assets5.lottiefiles.com/packages/lf20_JkRdsa6Exx.json"),
    Exercise("Stretching", "https://assets3.lottiefiles.com/packages/lf20_VBz0xZZjd6.json"),
    Exercise("Boxing", "https://assets3.lottiefiles.com/packages/lf20_z85kfusv.json")
)