package com.henriquesbraga.notesappktsq.fragments.editor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.henriquesbraga.notesappktsq.NotesApplication
import com.henriquesbraga.notesappktsq.R
import com.henriquesbraga.notesappktsq.databinding.FragmentEditorBinding
import com.henriquesbraga.notesappktsq.model.Note


class EditorFragment : Fragment(), EditorContract.View {

  private var _binding: FragmentEditorBinding? = null
  private val binding get() = _binding!!

  private lateinit var editorView: EditorView
  private lateinit var presenter: EditorPresenter
  private lateinit var note: Note
  private lateinit var fragmentMenu: Menu

  override fun showProgress() {
    editorView.showProgress()
  }

  override fun hideProgress() {
    editorView.hideProgress()
  }

  override fun onRequestSuccess(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
  }

  override fun onRequestError(message: String?) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    setHasOptionsMenu(true)
    _binding = FragmentEditorBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    editorView = EditorView(binding)
    val noteRepository = (requireContext().applicationContext as NotesApplication).noteRepository

    presenter = EditorPresenter(this, noteRepository)

    note = (arguments?.getSerializable("note") as? Note)!!
    editorView.setDataFromIntentExtra(note)
    if(note.id == null) {
      editorView.editMode()
    }

  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.menu_editor, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)
    fragmentMenu = menu

    if (note.id != null) {
      fragmentMenu.findItem(R.id.update).isVisible = true
      fragmentMenu.findItem(R.id.delete).isVisible = true
      fragmentMenu.findItem(R.id.edit).isVisible = true
      fragmentMenu.findItem(R.id.save).isVisible = false
    }
    else {
      fragmentMenu.findItem(R.id.save).isVisible = true
      fragmentMenu.findItem(R.id.update).isVisible = false
      fragmentMenu.findItem(R.id.delete).isVisible = false
      fragmentMenu.findItem(R.id.edit).isVisible = false
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      //save
      R.id.save -> {
        if (editorView.validateFields()) {
          val note = editorView.createNote()
          presenter.saveNote(note,findNavController())
        }
        true
      }
      R.id.edit -> {
        //edit
        editorView.editMode()
        fragmentMenu.findItem(R.id.edit).isVisible = false

        true
      }
      R.id.update -> {
        //update
        if (editorView.validateFields()) {
          val newNote = editorView.updateNote(note)
          presenter.updateNote(newNote, findNavController())
        }
        true
      }
      R.id.delete -> {
        //delete
        editorView.deleteNote(note.id!!, object: EditorContract.CallBack{
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
    presenter.deleteNote(id, findNavController())
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}