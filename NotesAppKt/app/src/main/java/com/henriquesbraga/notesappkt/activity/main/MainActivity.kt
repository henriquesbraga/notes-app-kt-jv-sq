package com.henriquesbraga.notesappkt.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henriquesbraga.notesappkt.R
import com.henriquesbraga.notesappkt.activity.editor.EditorActivity
import com.henriquesbraga.notesappkt.activity.main.adapter.ItemClickListener
import com.henriquesbraga.notesappkt.activity.main.adapter.MainAdapter
import com.henriquesbraga.notesappkt.model.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    companion object{
        private const val INTENT_EDIT = 200
        private const val INTENT_ADD = 100
    }

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var itemClickListener: ItemClickListener


    var listNotes: ArrayList<Note> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1 - get Recycler view
        var recycler = findViewById<RecyclerView>(R.id.recycler_view)

        //2 - set layout
        recycler.layoutManager = LinearLayoutManager(this)

        //Button to create new note
        add.setOnClickListener{
            startActivityForResult(Intent(this, EditorActivity::class.java), INTENT_ADD)
        }

        //Presenter
        presenter = MainPresenter(this)
        presenter.getData()

        //swipe gesture
        swipe_refresh.setOnRefreshListener { presenter.getData() }

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

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == INTENT_ADD && resultCode == RESULT_OK){
            presenter.getData()
        }
        else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK){
            presenter.getData()
        }
    }


    override fun showLoading() {
        swipe_refresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh.isRefreshing = false
    }

    override fun onGetResult(notes: ArrayList<Note>) {
        adapter = MainAdapter(this, notes, itemClickListener)
        recycler_view.adapter = adapter
        listNotes = notes

    }

    override fun onErrorLoading(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}