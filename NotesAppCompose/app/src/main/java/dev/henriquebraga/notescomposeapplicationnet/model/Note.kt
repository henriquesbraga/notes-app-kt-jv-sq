package dev.henriquebraga.notescomposeapplicationnet.model

import java.io.Serializable


data class Note (
    val id: Int = 0,
    val title: String = "",
    val note: String = "",
    val color: String = "",
    val date: String = ""
): Serializable
