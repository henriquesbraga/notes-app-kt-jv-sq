package com.henriquesbraga.notesappktsq.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.henriquesbraga.notesappktsq.repository.constants.DatabaseConstants

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL(CREATE_TABLE_NOTE)
  }

  override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    TODO("Not yet implemented")
  }

  companion object {
    private const val DATABASE_VERSION = 1
    private const val DATABASE_NAME = "notes.db"

    private const val CREATE_TABLE_NOTE =
      ("CREATE TABLE " + DatabaseConstants.NOTE.TABLE_NAME + " ("
        + DatabaseConstants.NOTE.COLUMNS.ID + " integer primary key autoincrement, "
        + DatabaseConstants.NOTE.COLUMNS.TITLE + " text, "
        + DatabaseConstants.NOTE.COLUMNS.NOTE + " text, "
        + DatabaseConstants.NOTE.COLUMNS.COLOR + " string, "
        + DatabaseConstants.NOTE.COLUMNS.DATE + " string);")
  }
}