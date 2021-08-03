package com.henriquesbraga.notesappktsq.activity.main.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.henriquesbraga.notesappktsq.R
import com.henriquesbraga.notesappktsq.model.Note

class MainViewHolder(itemView: View, itemClickListener: ItemClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var itemClickListener = itemClickListener

    fun bind(note: Note){
        var tv_title: TextView = itemView.findViewById(R.id.tv_title)
        tv_title.text = note.title
        var tv_note = itemView.findViewById<TextView>(R.id.tv_note)
        tv_note.text = note.note
        var tv_date = itemView.findViewById<TextView>(R.id.tv_date)
        tv_date.text = note.date
        var card_item = itemView.findViewById<androidx.cardview.widget.CardView>(R.id.card_item)


        card_item.setBackgroundColor(note.color)

        card_item.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        itemClickListener?.onItemClick(v, adapterPosition)
    }

}
