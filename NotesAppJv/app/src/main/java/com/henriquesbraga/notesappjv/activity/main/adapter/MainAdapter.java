package com.henriquesbraga.notesappjv.activity.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.henriquesbraga.notesappjv.R;
import com.henriquesbraga.notesappjv.model.Note;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private Context context;
    private List<Note> notes;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new MainViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
