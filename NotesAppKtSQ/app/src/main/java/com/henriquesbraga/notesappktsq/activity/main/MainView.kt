package com.henriquesbraga.notesappktsq.activity.main

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriquesbraga.notesappktsq.activity.editor.EditorActivity
import com.henriquesbraga.notesappktsq.activity.main.adapter.MainAdapter
import com.henriquesbraga.notesappktsq.databinding.ActivityMainBinding
import com.henriquesbraga.notesappktsq.model.Note

class MainView(private val binding: ActivityMainBinding) {

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