package dev.henriquebraga.notescomposeapplicationnet.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateAsString(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault())
    val currentDate = Date()
    return dateFormat.format(currentDate)
}