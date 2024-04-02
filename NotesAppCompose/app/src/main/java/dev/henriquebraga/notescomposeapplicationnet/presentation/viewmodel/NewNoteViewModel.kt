package dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.henriquebraga.notescomposeapplicationnet.model.Note
import dev.henriquebraga.notescomposeapplicationnet.network.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    // title
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    // Body
    private val _body = MutableStateFlow("")
    val body = _body.asStateFlow()

    // Color
    private val _color = MutableStateFlow(0)
    val color = _color.asStateFlow()

    // Data Loading
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()



    fun updateTitle(title: String) {
        _title.value = title
    }

    fun updateBody(body: String) {
        _body.value = body
    }

    fun updateColor(color: Int) {
        _color.value = color
    }

    fun clearData() {
        _title.value = ""
        _body.value = ""
        _color.value = 0
    }

    private fun handleColor(color: Int): Int {
        return when(color) {
            -2234644 -> 0
            -8469267 -> 1
            -3081091 -> 2
            -1222758 -> 3
            -13963914 -> 4
            else -> -2234644
        }
    }

    fun getNoteById(noteId: Int) {
        if(!_isLoading.value) _isLoading.value = true
        viewModelScope.launch {
            println("I'm searching for a note...")
            try {
                val response = apiService.getNote(noteId)
                if (response.isSuccessful) {
                    println(response.body().toString())
                    _title.value = response.body()!!.title
                    _body.value  = response.body()!!.note
                    _color.value = handleColor(response.body()!!.color.toInt())
                    _isLoading.value = false
                }
                else {
                    println("err: " + response.body().toString())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }


    fun sendNote(navController: NavController) {
        viewModelScope.launch {
            val color = when (_color.value) {
                0 -> -2234644
                1 -> -8469267
                2 -> -3081091
                3 -> -1222758
                4 -> -13963914
                else -> -2234644
            }
            val note = Note(
                id = 0,
                title = _title.value,
                note = _body.value,
                color = color.toString()
            )
            try {
                val response = apiService.insert(note)
                if (response.isSuccessful) {
                    println(response.body().toString())
                    navController.popBackStack()
                } else {
                    println("err: " + response.body().toString())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}