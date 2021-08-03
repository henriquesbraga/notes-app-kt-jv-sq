package com.henriquesbraga.notesappjv.activity.main.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.henriquesbraga.notesappjv.R;
import com.henriquesbraga.notesappjv.model.Note;

public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView tv_title, tv_note, tv_date;
    CardView card_item;
    ItemClickListener itemClickListener;

    public MainViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    public void bind(Note note){
        tv_title = itemView.findViewById(R.id.tv_title);
        tv_title.setText(note.getTitle());
        tv_note = itemView.findViewById(R.id.tv_note);
        tv_note.setText(note.getNote());
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_date.setText(note.getDate());
        card_item = itemView.findViewById(R.id.card_item);
        card_item.setCardBackgroundColor(note.getColor());
        card_item.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClick(view, getAdapterPosition());
    }
}
