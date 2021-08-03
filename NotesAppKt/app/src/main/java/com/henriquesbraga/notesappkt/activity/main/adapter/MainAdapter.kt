package com.henriquesbraga.notesappkt.activity.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henriquesbraga.notesappkt.R
import com.henriquesbraga.notesappkt.model.Note

class MainAdapter(context: Context, notes: ArrayList<Note>, itemClickListener: ItemClickListener): RecyclerView.Adapter<MainViewHolder>() {

    var context = context
    var notes = notes
    var itemClickListener = itemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return MainViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(notes[position])

    }

    override fun getItemCount(): Int {
        return notes.count()
    }

}