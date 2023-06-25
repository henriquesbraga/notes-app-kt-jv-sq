package com.henriquesbraga.notesappktsq.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriquesbraga.notesappktsq.NotesApplication
import com.henriquesbraga.notesappktsq.activity.editor.EditorActivity
import com.henriquesbraga.notesappktsq.activity.main.adapter.ItemClickListener
import com.henriquesbraga.notesappktsq.activity.main.adapter.MainAdapter
import com.henriquesbraga.notesappktsq.databinding.ActivityMainBinding
import com.henriquesbraga.notesappktsq.model.Note

class MainActivity : AppCompatActivity(), MainContract.View {

    //consts
    companion object {
        private const val INTENT_EDIT = 200
        private const val INTENT_ADD = 100
    }

    private lateinit var view: MainView
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var itemClickListener: ItemClickListener

    var listNotes: ArrayList<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = MainView(ActivityMainBinding.inflate(layoutInflater))
        setContentView(view.root)

        supportActionBar?.title = "Suas Notas"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        //layout da lista
        view.setLayout()

        //handler do swipe e button
        view.elementsListener(object: MainContract.MainCallBack{
            override fun getDataFromRepository() {
                getData()
            }

            override fun startActivityFromButton() {
                startActivity()
            }
        })

        itemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                val id: Int = listNotes[position].id!!
                val title: String = listNotes[position].title
                val note: String = listNotes[position].note
                val color: Int = listNotes[position].color

                val intent = Intent(applicationContext, EditorActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("title", title)
                intent.putExtra("note", note)
                intent.putExtra("color", color)
                startActivityForResult(intent, INTENT_EDIT)
            }
        }

        val noteRepository = (applicationContext as NotesApplication).noteRepository

        //Presenter
        presenter = MainPresenter(this, noteRepository)
        presenter.getData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData()
        } else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData()
        }
    }

    override fun showLoading() {
        view.showLoading()
    }

    override fun hideLoading() {
        view.hideLoading()
    }

    override fun onErrorLoading(message: String) {
        view.onErrorLoading(message)
    }

    override fun onGetResult(notes: ArrayList<Note>) {
        adapter = MainAdapter(this, notes, itemClickListener)
        view.onGetResult(adapter)
        listNotes = notes
    }

    fun getData(){
        presenter.getData()
    }

    fun startActivity(){
        startActivityForResult(Intent(this, EditorActivity::class.java), INTENT_ADD)
    }


}