package dev.henriquebraga.notescomposeapplicationnet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.henriquebraga.notescomposeapplicationnet.navigation.NavigationHandler
import dev.henriquebraga.notescomposeapplicationnet.presentation.view.NotesView
import dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel.NewNoteViewModel
import dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel.NotesViewModel
import dev.henriquebraga.notescomposeapplicationnet.ui.theme.NotesComposeApplicationNetTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesComposeApplicationNetTheme {
                val notesViewModel = viewModel<NotesViewModel>()
                val newNoteViewModel = viewModel<NewNoteViewModel>()
                NavigationHandler(
                    notesViewModel = notesViewModel,
                    newNoteViewModel = newNoteViewModel
                )
            }
        }
    }
}