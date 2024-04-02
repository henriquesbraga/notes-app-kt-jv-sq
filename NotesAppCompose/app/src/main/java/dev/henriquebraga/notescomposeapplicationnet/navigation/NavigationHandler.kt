package dev.henriquebraga.notescomposeapplicationnet.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.henriquebraga.notescomposeapplicationnet.model.Note
import dev.henriquebraga.notescomposeapplicationnet.presentation.view.NewNoteView
import dev.henriquebraga.notescomposeapplicationnet.presentation.view.NotesView
import dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel.NewNoteViewModel
import dev.henriquebraga.notescomposeapplicationnet.presentation.viewmodel.NotesViewModel


@Composable
fun NavigationHandler(
    notesViewModel: NotesViewModel,
    newNoteViewModel: NewNoteViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "NotesView",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable("NotesView") {
            NotesView(
                viewModel = notesViewModel,
                navController = navController
            )
        }
        composable(
            route = "NewNoteView/{note}",
            arguments = listOf(navArgument("note") {type = NavType.IntType})
        ) {
            val args = it.arguments?.getInt("note")
            NewNoteView(
                viewModel = newNoteViewModel,
                navController = navController,
                noteId = args
            )
        }
    }
}