package com.henriquesbraga.notesappktsq.activity.main

import android.content.Context
import android.util.Log
import com.henriquesbraga.notesappktsq.model.Note
import com.henriquesbraga.notesappktsq.repository.NoteRepository
import java.lang.Exception

class MainPresenter(view: MainView, context: Context) {

    private var view = view
    private val mNoteRepository = NoteRepository.getInstance(context)

    var list: ArrayList<Note> = arrayListOf()
    fun getData(){
        try{
            view.showLoading();
            list = mNoteRepository.getAll()
            view.hideLoading()
            view.onGetResult(list)
        }
        catch (e: Exception){
            view.onErrorLoading(e.message.toString())
        }
    }
}