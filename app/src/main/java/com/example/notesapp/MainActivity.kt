package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.data.database.NoteDatabase
import com.example.notesapp.data.repository.NoteRepository
import com.example.notesapp.data.repository.UserPreferencesRepository
import com.example.notesapp.navigation.NavGraph
import com.example.notesapp.ui.theme.NotesAppTheme
import com.example.notesapp.viewmodel.NoteViewModel
import com.example.notesapp.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = NoteDatabase.getDatabase(this)
        val repository = NoteRepository(database.noteDao())
        val userPrefs = UserPreferencesRepository(this)
        val viewModel = ViewModelProvider(this, NoteViewModelFactory(repository))[NoteViewModel::class.java]

        setContent {
            val onboardingState = userPrefs.hasCompletedOnboarding.collectAsState(initial = null)
            val onboardingFinished = onboardingState.value

            val scope = rememberCoroutineScope()

            NotesAppTheme {
                if (onboardingFinished != null) {
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        viewModel = viewModel,
                        hasCompletedOnboarding = onboardingFinished,
                        onOnboardingFinish = {
                            scope.launch {
                                userPrefs.saveOnboardingCompleted()
                                navController.navigate("list") {
                                    popUpTo("onboarding") { inclusive = true }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}