package com.henriquesbraga.notesappktsq.model

import java.io.Serializable

data class Note(
  val id: Int? = null,
  val title: String = "",
  val note: String = "",
  val color: Int = -1,
  val date: String = ""
): Serializable
