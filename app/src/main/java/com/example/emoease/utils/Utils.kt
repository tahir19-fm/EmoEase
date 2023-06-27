package com.example.emoease.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.room.TypeConverter

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: () -> Unit
) = composed(
    factory = {
        this.then(
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() }
            )
        )
    }
)

fun stringToList(string: String):List<String>{
    return string.split(",").toList()
}

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").toList()
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

}