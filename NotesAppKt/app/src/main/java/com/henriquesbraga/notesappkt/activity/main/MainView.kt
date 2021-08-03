package com.henriquesbraga.notesappkt.activity.main

import com.henriquesbraga.notesappkt.model.Note

interface MainView {
    fun showLoading();
    fun hideLoading();
    fun onGetResult(notes: ArrayList<Note>);
    fun onErrorLoading(message: String);
}