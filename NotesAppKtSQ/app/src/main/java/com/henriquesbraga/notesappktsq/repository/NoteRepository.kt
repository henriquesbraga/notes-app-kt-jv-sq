package com.henriquesbraga.notesappktsq.repository

import android.content.ContentValues
import android.content.Context
import com.henriquesbraga.notesappktsq.model.Note
import com.henriquesbraga.notesappktsq.repository.constants.DatabaseConstants
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteRepository private constructor(context: Context) {

    private var mNoteDatabaseHelper = DatabaseHelper(context)

    companion object {
        private lateinit var repository: NoteRepository     //Guarda a instancia da classe
        fun getInstance(context: Context): NoteRepository {
            if(!::repository.isInitialized){                //Verifica se ja foi inicializada
                repository = NoteRepository(context)
            }
            return repository
        }
    }

    //Retorna a lista com as notas
    fun getAll(): ArrayList<Note> {
        val list: ArrayList<Note> = arrayListOf()

        return try {
            val db = mNoteDatabaseHelper.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM " + DatabaseConstants.NOTE.TABLE_NAME, null, null )

            if(cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val id = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NOTE.COLUMNS.ID))
                    val title = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NOTE.COLUMNS.TITLE))
                    val noteTxt = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NOTE.COLUMNS.NOTE))
                    val color = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NOTE.COLUMNS.COLOR))
                    val date = cursor.getString(cursor.getColumnIndex(DatabaseConstants.NOTE.COLUMNS.DATE))
                    val note = Note(id.toInt(), title, noteTxt, color.toInt())
                    note.date = date
                    list.add(note)
                }
                cursor.close()
            }
            list
        }
        catch (e: Exception){
            list
        }
    }

    //Salva uma nota
    fun save(note: Note): Boolean {

        val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date = format.format(Date())

        return try{
            val db = mNoteDatabaseHelper.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(DatabaseConstants.NOTE.COLUMNS.TITLE, note.title)
            contentValues.put(DatabaseConstants.NOTE.COLUMNS.NOTE, note.note)
            contentValues.put(DatabaseConstants.NOTE.COLUMNS.COLOR, note.color.toString())
            contentValues.put(DatabaseConstants.NOTE.COLUMNS.DATE, date)

            db.insert(DatabaseConstants.NOTE.TABLE_NAME, null, contentValues)
            true
        }
        catch (e: Exception){false}
    }

    //Atualiza uma nota
    fun update(note: Note): Boolean {
        return try{
            val db = mNoteDatabaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DatabaseConstants.NOTE.COLUMNS.TITLE, note.title)
            contentValues.put(DatabaseConstants.NOTE.COLUMNS.NOTE, note.note)
            contentValues.put(DatabaseConstants.NOTE.COLUMNS.COLOR, note.color)

            val selection = DatabaseConstants.NOTE.COLUMNS.ID + " =?"
            val args = arrayOf(note.id.toString())
            db.update(DatabaseConstants.NOTE.TABLE_NAME, contentValues, selection, args)
            true
        }
        catch (e:Exception){false}
    }

    //Deleta uma nota
    fun delete(id: Int): Boolean {
        return try{
            val db = mNoteDatabaseHelper.writableDatabase
            val selection = DatabaseConstants.NOTE.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            db.delete(DatabaseConstants.NOTE.TABLE_NAME, selection, args)
            true
        }
        catch (e: Exception) {false}
    }


}