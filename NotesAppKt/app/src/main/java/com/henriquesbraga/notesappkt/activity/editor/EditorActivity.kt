package com.henriquesbraga.notesappkt.activity.editor

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import com.henriquesbraga.notesappkt.R
import com.henriquesbraga.notesappkt.model.Note
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity(), EditorView {

    private lateinit var presenter: EditorPresenter
    private lateinit var progressbar: ProgressBar       //ProgressDialog is deprecated
    var actionMenu: Menu? = null
    private var id = 0
    private var title = ""
    private var note = ""
    private var color = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        palette.setOnColorSelectedListener { color = it }

        //progress bar
        progressbar = progress_circular
        hideProgress()

        //presenter
        presenter = EditorPresenter(this)

        //intent from main activity
        val intent = intent
        id = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title").toString()
        note = intent.getStringExtra("note").toString()
        color = intent.getIntExtra("color", 0)
        setDataFromIntentExtra()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_editor, menu)
        actionMenu = menu

        if(id != 0){
            actionMenu!!.findItem(R.id.edit).isVisible = true
            actionMenu!!.findItem(R.id.delete).isVisible = true
            actionMenu!!.findItem(R.id.save).isVisible = false
            actionMenu!!.findItem(R.id.update).isVisible = false
        } else {
            actionMenu!!.findItem(R.id.edit).isVisible = false
            actionMenu!!.findItem(R.id.delete).isVisible = false
            actionMenu!!.findItem(R.id.save).isVisible = true
            actionMenu!!.findItem(R.id.update).isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = et_title.text.toString().trim { it <= ' ' }
        val note = et_note.text.toString().trim { it <= ' ' }
        val color = color

        return when (item.itemId) {
            R.id.save -> {
                //save
                when {
                    title.isEmpty() -> {
                        et_title.error = "Preencha o titulo"
                    }
                    note.isEmpty() -> {
                        et_note.error = "Preencha a nota"
                    }
                    else -> {
                        var note = Note(null, title, note, color)
                        presenter.saveNote(note)
                    }
                }
                true
            }
            R.id.edit -> {
                editMode()
                actionMenu!!.findItem(R.id.edit).isVisible = false
                actionMenu!!.findItem(R.id.delete).isVisible = false
                actionMenu!!.findItem(R.id.save).isVisible = false
                actionMenu!!.findItem(R.id.update).isVisible = true
                true
            }
            R.id.update -> {
                //update
                when {
                    title.isEmpty() -> {
                        et_title.error = "Preencha o titulo"
                    }
                    note.isEmpty() -> {
                        et_note.error = "Preencha a nota"
                    }
                    else -> {
                        var note = Note(id, title, note, color)
                        presenter.updateNote(note)
                    }
                }
                true
            }
            R.id.delete -> {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Deletar nota")
                alertDialog.setMessage("Deseja deletar a nota?")
                alertDialog.setNegativeButton("Sim") { dialog: DialogInterface, which: Int ->
                    dialog.dismiss()
                    presenter.deleteNote(id)
                }
                alertDialog.setPositiveButton(
                    "Não"
                ) { dialog: DialogInterface, wich: Int -> dialog.dismiss() }
                alertDialog.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showProgress() {
        progressbar.isVisible = true
    }

    override fun hideProgress() {
        progressbar.isVisible = false
    }

    override fun onRequestSuccess(message: String?) {
        Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
        setResult(RESULT_OK)
        finish() //back to main activity
    }

    override fun onRequestError(message: String?) {
        Toast.makeText(this@EditorActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setDataFromIntentExtra() {
        if (id != 0) {
            et_title.setText(title)
            et_note.setText(note)
            palette.setSelectedColor(color)
            supportActionBar!!.title = "Update Note"
            readMode()
        } else {
            //default color setup
            palette.setSelectedColor(resources.getColor(R.color.white))
            color = resources.getColor(R.color.white)
            editMode()
        }
    }

    private fun editMode() {
        et_title.isFocusableInTouchMode = true
        et_note.isFocusableInTouchMode = true
        palette.isEnabled = true
    }

    private fun readMode() {
        et_title.isFocusableInTouchMode = false
        et_note.isFocusableInTouchMode = false
        et_title.isFocusable = false
        et_note.isFocusable = false
        palette.isEnabled = false
    }

}