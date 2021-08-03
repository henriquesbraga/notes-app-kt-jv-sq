package com.henriquesbraga.notesappktsq.repository.constants

class DatabaseConstants private constructor(){
    object NOTE {
        const val TABLE_NAME = "notes"
        object COLUMNS {
            const val ID = "id"
            const val TITLE = "title"
            const val NOTE = "note"
            const val COLOR = "color"
            const val DATE = "date"
        }
    }
}