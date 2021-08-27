package com.henriquesbraga.notesappktsq

import android.app.Application
import com.henriquesbraga.notesappktsq.repository.DatabaseHelper
import com.henriquesbraga.notesappktsq.repository.NoteRepository

class NotesApplication : Application() {

    private lateinit var _databaseHelper: DatabaseHelper

    private lateinit var _noteRepository: NoteRepository
    val noteRepository
        get() = _noteRepository

    override fun onCreate() {
        super.onCreate()
        _databaseHelper = DatabaseHelper(applicationContext)
        _noteRepository = NoteRepository(_databaseHelper)
    }

}