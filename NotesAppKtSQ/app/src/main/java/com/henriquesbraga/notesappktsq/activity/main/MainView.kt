package com.henriquesbraga.notesappktsq.activity.main

import com.henriquesbraga.notesappktsq.model.Note

interface MainView {
    fun showLoading();
    fun hideLoading();
    fun onGetResult(notes: ArrayList<Note>);
    fun onErrorLoading(message: String);
}