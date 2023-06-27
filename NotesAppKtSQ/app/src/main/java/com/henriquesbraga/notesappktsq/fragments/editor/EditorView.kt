package com.henriquesbraga.notesappktsq.fragments.editor

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.view.isVisible
import com.henriquesbraga.notesappktsq.R
import com.henriquesbraga.notesappktsq.databinding.FragmentEditorBinding
import com.henriquesbraga.notesappktsq.model.Note

class EditorView(private val binding: FragmentEditorBinding) {

  private val root: View
    get() = binding.root

  @ColorRes
  private var selectedColor: Int = -1

  init {
    binding.paletteView.setOnColorSelectedListener { selectedColor = it }
    binding.paletteView.setSelectedColor(binding.root.resources.getColor(R.color.white))
  }

  fun showProgress() {
    binding.progressCircular.isVisible = true
  }

  fun hideProgress() {
    binding.progressCircular.isVisible = false
  }

  fun editMode() {
    binding.titleEditText.isFocusableInTouchMode = true
    binding.noteEditText.isFocusableInTouchMode = true
    binding.paletteView.isEnabled = true
  }


  fun readMode() {
    binding.titleEditText.isFocusableInTouchMode = false
    binding.noteEditText.isFocusableInTouchMode = false
    binding.titleEditText.isFocusable = false
    binding.noteEditText.isFocusable = false
    binding.paletteView.isEnabled = false
  }

  fun createNote(): Note {
    return Note(
      id = null,
      title = binding.titleEditText.text.toString(),
      note = binding.noteEditText.text.toString(),
      color = selectedColor
    )
  }

  fun updateNote(note:Note): Note{
    return Note(
      id = note.id,
      title = binding.titleEditText.text.toString(),
      note = binding.noteEditText.text.toString(),
      color = selectedColor
    )
  }

  fun deleteNote(id: Int, callback: EditorContract.CallBack){

    val alertDialog = AlertDialog.Builder(root.context)
    alertDialog.setTitle("Deletar nota")
    alertDialog.setMessage("Deseja deletar a nota?")
    alertDialog.setNegativeButton("Sim") { dialog: DialogInterface, _: Int ->
      dialog.dismiss()
      callback.deleteNote(id)
    }
    alertDialog.setPositiveButton(
      "Não"
    ) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
    alertDialog.show()
  }

  fun validateFields(): Boolean {
    var isValid = true

    with(binding) {
      if (titleEditText.text.isEmpty()) {
        isValid = false
        titleEditText.error = "Preencha o titulo"
      }
      if (noteEditText.text.isEmpty()) {
        isValid = false
        noteEditText.error = "Preencha a nota"
      }
    }

    return isValid
  }

  fun setDataFromIntentExtra(note: Note) {
    if (note.id != 0) {
      binding.titleEditText.setText(note.title)
      binding.noteEditText.setText(note.note)
      binding.paletteView.setSelectedColor(note.color)
      readMode()
    } else {
      editMode()
    }
  }
}
