package com.henriquesbraga.notesappktsq.activity.editor

interface EditorView {
    fun showProgress()
    fun hideProgress()
    fun onRequestSuccess(message: String?)
    fun onRequestError(message: String?)
}