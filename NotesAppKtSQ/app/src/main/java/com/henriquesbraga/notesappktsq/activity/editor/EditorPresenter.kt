package com.henriquesbraga.notesappktsq.activity.editor

import com.henriquesbraga.notesappktsq.model.Note
import com.henriquesbraga.notesappktsq.repository.NoteRepository

class EditorPresenter(
    private val view: EditorContract.View,
    private val noteRepository: NoteRepository
) {

    fun saveNote(note: Note) {
        view.showProgress()
        val response = noteRepository.save(note)
        view.hideProgress()
        if (response) {
            view.onRequestSuccess("Nota Salva com sucesso!")
        } else {
            view.onRequestError("Erro ao salvar!")
        }
    }

    fun updateNote(note: Note) {
        view.showProgress()
        val response = noteRepository.update(note)
        view.hideProgress()
        if (response) {
            view.onRequestSuccess("Nota atualizada com sucesso!")
        } else {
            view.onRequestError("Erro ao atualizar!")
        }
    }

    fun deleteNote(id: Int) {
        view.showProgress()
        val response = noteRepository.delete(id)
        if (response) {
            view.onRequestSuccess("Nota deletada com sucesso!")
        } else {
            view.onRequestError("Erro ao deletar!")
        }
    }

}