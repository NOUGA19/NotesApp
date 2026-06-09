package com.example.notesapp.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notesapp.ui.components.DeleteConfirmationDialog // Import du nouveau composant
import com.example.notesapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    noteId: Int,
    viewModel: NoteViewModel,
    onBack: () -> Unit,
    onEditClick: () -> Unit
)  {
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())
    val note = notes.find { it.id == noteId }

    // 1. Variable d'état pour savoir si on affiche le dialogue
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                },

                actions = {
                    IconButton(onClick = { note?.let { viewModel.togglePin(it) } }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Épingler",
                            tint = if (note?.isPinned == true) Color(0xFFFFD700) else Color.LightGray
                        )
                    }

                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Modifier"
                        )
                    }

                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Supprimer"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // 2. Si showDeleteDialog est vrai, on affiche le composant
        if (showDeleteDialog) {
            DeleteConfirmationDialog(
                onConfirm = {
                    note?.let {
                        viewModel.deleteNote(it)
                        showDeleteDialog = false
                        onBack()
                    }
                },
                onDismiss = { showDeleteDialog = false } // On ferme juste le dialogue
            )
        }

        note?.let { currentNote ->
            Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                Text(text = currentNote.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = currentNote.content, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}