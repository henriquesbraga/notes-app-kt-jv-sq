package com.henriquesbraga.notesappktsq.fragments.list

import com.henriquesbraga.notesappktsq.model.Note
import com.henriquesbraga.notesappktsq.repository.NoteRepository

class MainPresenter(
  private val view: MainContract.View,
  private val noteRepository: NoteRepository
) {

  var list: ArrayList<Note> = arrayListOf()
  fun getData() {
    try {
      view.showLoading();
      list = noteRepository.getAll()
      view.hideLoading()
      view.onGetResult(list)
    } catch (e: Exception) {
      view.onErrorLoading(e.message.toString())
    }
  }
}