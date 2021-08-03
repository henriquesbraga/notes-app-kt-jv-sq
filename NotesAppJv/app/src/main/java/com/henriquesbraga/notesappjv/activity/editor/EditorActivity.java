package com.henriquesbraga.notesappjv.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.henriquesbraga.notesappjv.R;
import com.henriquesbraga.notesappjv.api.ApiInterface;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView{

    EditorPresenter presenter;
    ProgressBar progress_circular;
    EditText et_title, et_note;
    ApiInterface apiInterface;
    SpectrumPalette palette;
    int color, id;
    String title, note;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_title = findViewById(R.id.et_title);
        et_note = findViewById(R.id.et_note);
        palette = findViewById(R.id.palette);

        palette.setOnColorSelectedListener((clr) -> color = clr);

        //progress bar
        progress_circular = findViewById(R.id.progress_circular);

        presenter = new EditorPresenter(this);
        hideProgress();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");
        note = intent.getStringExtra("note");
        color = intent.getIntExtra("color", 0);

        setDataFromIntentExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu = menu;

        if(id != 0){
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        }
        else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String title = et_title.getText().toString().trim();
        String note = et_note.getText().toString().trim();
        int color = this.color;


        switch (item.getItemId()) {
            case R.id.save:
                //save
                if(title.isEmpty()){
                    et_title.setError("Preencha o titulo");
                }
                else if(note.isEmpty()){
                    et_note.setError("Preencha a nota");
                }
                else{
                    presenter.saveNote(title, note, color);
                }
                return true;

            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);
                return true;

            case R.id.update:
                //update
                if(title.isEmpty()){
                    et_title.setError("Preencha o titulo");
                }
                else if(note.isEmpty()){
                    et_note.setError("Preencha a nota");
                }
                else{
                    presenter.updateNote(id, title, note, color);
                }
                return true;

            case R.id.delete:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Deletar nota");
                alertDialog.setMessage("Deseja deletar a nota?");
                alertDialog.setNegativeButton("Sim", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteNote(id);
                });
                alertDialog.setPositiveButton("Não", (dialog, wich) -> { dialog.dismiss(); });
                alertDialog.show();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
        progress_circular.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress_circular.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish(); //back to main activity
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if(id != 0) {
            et_title.setText(title);
            et_note.setText(note);
            palette.setSelectedColor(color);
            getSupportActionBar().setTitle("Update Note");
            readMode();
        }
        else {
            //default color setup
            palette.setSelectedColor(getResources().getColor(R.color.white));
            color = getResources().getColor(R.color.white);
            editMode();
        }
    }

    private void editMode() {
        et_title.setFocusableInTouchMode(true);
        et_note.setFocusableInTouchMode(true);
        palette.setEnabled(true);
    }

    private void readMode() {
        et_title.setFocusableInTouchMode(false);
        et_note.setFocusableInTouchMode(false);
        et_title.setFocusable(false);
        et_note.setFocusable(false);
        palette.setEnabled(false);
    }

}
