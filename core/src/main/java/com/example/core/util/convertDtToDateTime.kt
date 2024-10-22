package com.example.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun getDateTime(dt: Int): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(Date(dt * 1000L))
}