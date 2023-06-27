package com.henriquesbraga.notesappktsq.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.henriquesbraga.notesappktsq.NotesApplication
import com.henriquesbraga.notesappktsq.R
import com.henriquesbraga.notesappktsq.databinding.FragmentListBinding
import com.henriquesbraga.notesappktsq.fragments.list.adapter.ItemClickListener
import com.henriquesbraga.notesappktsq.fragments.list.adapter.MainAdapter
import com.henriquesbraga.notesappktsq.model.Note

class ListFragment : Fragment(), MainContract.View {

  private var _binding: FragmentListBinding? = null
  private val binding get() = _binding!!

  private lateinit var mainView: MainView
  private lateinit var presenter: MainPresenter
  private lateinit var adapter: MainAdapter
  private lateinit var itemClickListener: ItemClickListener

  var listNotes: ArrayList<Note> = arrayListOf()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mainView = MainView(_binding!!)

    //layout da lista
    mainView.setLayout()

    //handler do swipe e button
    mainView.elementsListener(object: MainContract.MainCallBack{
      override fun getDataFromRepository() {
        getData()
      }

      override fun startActivityFromButton() {
        findNavController().navigate(
          R.id.action_listFragment_to_editorFragment,
          bundleOf("note" to Note())
        )
      }
    })

    itemClickListener = object : ItemClickListener {
      override fun onItemClick(view: View?, position: Int) {
        findNavController().navigate(
          R.id.action_listFragment_to_editorFragment,
            bundleOf("note" to Note (
              id = listNotes[position].id!!,
              title = listNotes[position].title,
              note = listNotes[position].note,
              color = listNotes[position].color
            )
          )
        )
      }
    }
    val noteRepository = (requireContext().applicationContext as NotesApplication).noteRepository

    //Presenter
    presenter = MainPresenter(this, noteRepository)
    presenter.getData()
  }

  override fun showLoading() {
    mainView.showLoading()
  }

  override fun hideLoading() {
    mainView.hideLoading()
  }

  override fun onGetResult(notes: ArrayList<Note>) {
    adapter = MainAdapter(requireContext(), notes, itemClickListener)
    mainView.onGetResult(adapter)
    listNotes = notes
  }

  override fun onErrorLoading(message: String) {
    mainView.onErrorLoading(message)
  }

  fun getData(){
    presenter.getData()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentListBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}