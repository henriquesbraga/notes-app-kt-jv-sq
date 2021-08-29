package com.henriquesbraga.notesappktsq.activity.main

import com.henriquesbraga.notesappktsq.model.Note

interface MainContract {
    interface View {
        fun showLoading();
        fun hideLoading();
        fun onGetResult(notes: ArrayList<Note>);
        fun onErrorLoading(message: String);
    }

    interface MainCallBack {
        fun getDataFromRepository()
        fun startActivityFromButton()
    }

}