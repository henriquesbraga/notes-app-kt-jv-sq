package com.henriquesbraga.notesappktsq.fragments.list

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriquesbraga.notesappktsq.fragments.list.adapter.MainAdapter
import com.henriquesbraga.notesappktsq.databinding.ActivityMainBinding
import com.henriquesbraga.notesappktsq.databinding.FragmentListBinding

class MainView(private val binding: FragmentListBinding) {

  val root: View
    get() = binding.root

  fun showLoading() {
    binding.swipeRefresh.isRefreshing = true
  }

  fun hideLoading() {
    binding.swipeRefresh.isRefreshing = false
  }

  fun onErrorLoading(message: String) {
    Toast.makeText(root.context, message, Toast.LENGTH_SHORT).show()
  }


  fun setLayout(){
    //2 - set layout
    binding.recyclerView.layoutManager = LinearLayoutManager(root.context)
  }

  fun elementsListener(callback: MainContract.MainCallBack){
    //swipe gesture
    binding.swipeRefresh.setOnRefreshListener { callback.getDataFromRepository() }

    //Botão para criar outra nota
    binding.addButton.setOnClickListener { callback.startActivityFromButton() }
  }

  fun onGetResult(adapter: MainAdapter){
    binding.recyclerView.adapter = adapter
  }
}