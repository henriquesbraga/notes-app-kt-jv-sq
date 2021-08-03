package com.henriquesbraga.notesappktsq.activity.editor

import android.content.Context
import com.henriquesbraga.notesappktsq.model.Note
import com.henriquesbraga.notesappktsq.repository.NoteRepository


class EditorPresenter(view: EditorView, context: Context) {

    private var view = view
    private val mNoteRepository = NoteRepository.getInstance(context)

    fun saveNote(note: Note){
        view.showProgress()
        val response = mNoteRepository.save(note)
        view.hideProgress()
        if(response){
            view.onRequestSuccess("Nota Salva com sucesso!")
        }
        else {
            view.onRequestError("Erro ao salvar!")
        }
    }

    fun updateNote(note: Note) {
        view.showProgress()
        val response = mNoteRepository.update(note)
        view.hideProgress()
        if(response){
            view.onRequestSuccess("Nota atualizada com sucesso!")
        }
        else {
            view.onRequestError("Erro ao atualizar!")
        }
    }

    fun deleteNote(id: Int){
        view.showProgress()
        val response = mNoteRepository.delete(id)
        if(response){
            view.onRequestSuccess("Nota deletada com sucesso!")
        }
        else {
            view.onRequestError("Erro ao deletar!")
        }
    }

}