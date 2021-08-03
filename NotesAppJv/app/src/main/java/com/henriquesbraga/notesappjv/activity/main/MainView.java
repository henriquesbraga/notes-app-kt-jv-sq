package com.henriquesbraga.notesappjv.activity.main;

import com.henriquesbraga.notesappjv.model.Note;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
}
