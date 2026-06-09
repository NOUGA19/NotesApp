package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notesapp.ui.screens.detail.NoteDetailScreen
import com.example.notesapp.ui.screens.edit.NoteEditScreen
import com.example.notesapp.ui.screens.list.NoteListScreen
import com.example.notesapp.ui.screens.onboarding.OnboardingScreen // Import de l'écran d'accueil
import com.example.notesapp.viewmodel.NoteViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: NoteViewModel,
    hasCompletedOnboarding: Boolean, // Reçu depuis DataStore via MainActivity
    onOnboardingFinish: () -> Unit   // Action pour enregistrer la fin dans DataStore
) {
    NavHost(
        navController = navController,
        // Logique de démarrage : si l'onboarding est fini, on va à "list", sinon "onboarding"
        startDestination = if (hasCompletedOnboarding) "list" else "onboarding"
    ) {

        // --- ROUTE 1 : ÉCRAN D'INTRODUCTION ---
        composable("onboarding") {
            OnboardingScreen(onFinish = onOnboardingFinish)
        }

        // --- ROUTE 2 : LISTE DES NOTES ---
        composable("list") {
            NoteListScreen(
                viewModel = viewModel,
                onNoteClick = { id -> navController.navigate("detail/$id") },
                onAddNoteClick = { navController.navigate("edit/-1") }
            )
        }

        // --- ROUTE 3 : CRÉATION / MODIFICATION ---
        composable(
            route = "edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
            NoteEditScreen(
                noteId = noteId,
                viewModel = viewModel,
                onSaveSuccess = { navController.popBackStack() }
            )
        }

        // --- ROUTE 4 : DÉTAIL D'UNE NOTE ---
        composable(
            route = "detail/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            NoteDetailScreen(
                noteId = noteId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onEditClick = { navController.navigate("edit/$noteId") }
            )
        }
    }
}