package com.henriquesbraga.notesappktsq.fragments.editor

import androidx.navigation.NavController
import com.henriquesbraga.notesappktsq.model.Note
import com.henriquesbraga.notesappktsq.repository.NoteRepository

class EditorPresenter(
  private val view: EditorContract.View,
  private val noteRepository: NoteRepository
) {

  fun saveNote(note: Note, navController: NavController) {
    view.showProgress()
    val response = noteRepository.save(note)
    view.hideProgress()
    if (response) {
      view.onRequestSuccess("Nota Salva com sucesso!")
      navController.popBackStack()
    } else {
      view.onRequestError("Erro ao salvar!")
      navController.popBackStack()
    }
  }

  fun updateNote(note: Note, navController: NavController) {
    view.showProgress()
    val response = noteRepository.update(note)
    view.hideProgress()
    if (response) {
      view.onRequestSuccess("Nota atualizada com sucesso!")
      navController.popBackStack()
    } else {
      view.onRequestError("Erro ao atualizar!")
      navController.popBackStack()
    }
  }

  fun deleteNote(id: Int, navController: NavController) {
    view.showProgress()
    val response = noteRepository.delete(id)
    if (response) {
      view.onRequestSuccess("Nota deletada com sucesso!")
      navController.popBackStack()
    } else {
      view.onRequestError("Erro ao deletar!")
      navController.popBackStack()
    }
  }

}