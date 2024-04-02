package dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.henriquebraga.notescomposeapplicationnet.model.Note
import dev.henriquebraga.notescomposeapplicationnet.network.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {


    // notes
    private val _notes = MutableStateFlow(mutableListOf<Note>())
    val notes =_notes.asStateFlow()

    // notes loading status
    private val _notesStatus = MutableStateFlow(true)
    val notesStatus = _notesStatus.asStateFlow()


    init {
        getNotes()
    }

    fun getNotes() {
        if(!_notesStatus.value) _notesStatus.update { true }
        viewModelScope.launch {

            try {
                val response = apiService.getNotes()
                if(response.isSuccessful) {
                    println("notes status: ${response.isSuccessful}")
                    _notes.value.clear()
                    _notes.value.addAll(response.body()!!)
                    delay(2000L)
                    _notesStatus.update { false }
                }
            }
            catch (e: Exception) {
                println("error " + e.message)
            }
        }
    }
}