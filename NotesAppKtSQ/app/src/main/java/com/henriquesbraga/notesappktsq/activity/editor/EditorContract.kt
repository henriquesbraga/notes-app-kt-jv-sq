package com.henriquesbraga.notesappktsq.activity.editor

interface EditorContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun onRequestSuccess(message: String?)
        fun onRequestError(message: String?)
    }

    interface CallBack {
        fun deleteNote(id: Int)
    }

}
