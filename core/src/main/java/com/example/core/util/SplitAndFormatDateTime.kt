package com.example.core.util

import java.text.SimpleDateFormat
import java.util.Locale

fun changeFormatTime(dateTime: String): Pair<String?, String?> {
    val parts = dateTime.split(" ")
    val date = parts.getOrNull(0)
    val time = parts.getOrNull(1)
    val inputTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val outputTimeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    val timeDate = time?.let { inputTimeFormat.parse(it) }
    val formattedTime = timeDate?.let { outputTimeFormat.format(it) }
    return Pair(date, formattedTime)
}