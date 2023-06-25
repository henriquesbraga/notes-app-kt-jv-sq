package com.henriquesbraga.notesappktsq.activity.editor

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.henriquesbraga.notesappktsq.NotesApplication
import com.henriquesbraga.notesappktsq.R
import com.henriquesbraga.notesappktsq.databinding.ActivityEditorBinding
import com.henriquesbraga.notesappktsq.model.Note

class EditorActivity : AppCompatActivity(), EditorContract.View{

    private lateinit var view: EditorView
    private lateinit var presenter: EditorPresenter
    private lateinit var note: Note
    var actionMenu: Menu? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = EditorView(ActivityEditorBinding.inflate(layoutInflater))

        supportActionBar?.title = "Editar ou Salvar"

        setContentView(view.root)

        val noteRepository = (applicationContext as NotesApplication).noteRepository

        //presenter
        presenter = EditorPresenter(this, noteRepository)

        //intent from main activity
        val intent = intent

        note = Note(
            id = intent.getIntExtra("id", 0),
            title = intent.getStringExtra("title").toString(),
            note = intent.getStringExtra("note").toString(),
            color = intent.getIntExtra("color", 0)
        )

        view.setDataFromIntentExtra(note)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        val inflater = menuInflater

        inflater.inflate(R.menu.menu_editor, menu)
        actionMenu = menu

        if (menu == null) return true

        if (note.id != 0) {
            menu.findItem(R.id.edit).isVisible = true
            menu.findItem(R.id.delete).isVisible = true
            menu.findItem(R.id.save).isVisible = false
            menu.findItem(R.id.update).isVisible = false
        } else {
        menu.findItem(R.id.edit).isVisible = false
        menu.findItem(R.id.delete).isVisible = false
        menu.findItem(R.id.save).isVisible = true
        menu.findItem(R.id.update).isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //save
            R.id.save -> {
                if (view.validateFields()) {
                    val note = view.createNote()
                    presenter.saveNote(note)
                }
                true
            }
            R.id.edit -> {
                //edit
                view.editMode()
                supportActionBar!!.title = "Update Note"
                actionMenu!!.findItem(R.id.edit).isVisible = false
                actionMenu!!.findItem(R.id.delete).isVisible = false
                actionMenu!!.findItem(R.id.save).isVisible = false
                actionMenu!!.findItem(R.id.update).isVisible = true
                true
            }
            R.id.update -> {
                //update
                if (view.validateFields()) {
                    val newNote = view.updateNote(note)
                    presenter.updateNote(newNote)
                }
                true
            }
            R.id.delete -> {
                //delete
                view.deleteNote(note.id!!, object: EditorContract.CallBack{
                    override fun deleteNote(id: Int) {
                        removeNote(id)
                    }
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun removeNote(id: Int){
        presenter.deleteNote(id)
    }

    override fun showProgress() {
        view.showProgress()
    }

    override fun hideProgress() {
        view.hideProgress()
    }

    override fun onRequestSuccess(message: String?) {
        Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
        setResult(RESULT_OK)
        finish()
    }

    override fun onRequestError(message: String?) {
        Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
    }

}

