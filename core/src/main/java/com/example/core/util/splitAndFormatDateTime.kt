package com.example.core.util

import java.text.SimpleDateFormat
import java.util.Locale

fun changeFormatTime(dateTime: String): Pair<String, String> {
    val parts = dateTime.split(" ")
    val date = parts[0]
    val time = parts[1]  
    val inputTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val outputTimeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    val timeDate = inputTimeFormat.parse(time)
    val formattedTime = outputTimeFormat.format(timeDate)
    return Pair(date, formattedTime)
}