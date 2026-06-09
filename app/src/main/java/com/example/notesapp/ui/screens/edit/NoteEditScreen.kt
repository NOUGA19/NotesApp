package com.example.notesapp.ui.screens.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.model.NoteCategory
import com.example.notesapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    noteId: Int = -1, // -1 signifie qu'on crée une nouvelle note
    viewModel: NoteViewModel,
    onSaveSuccess: () -> Unit
) {
    // 1. On récupère la liste des notes pour trouver celle à modifier
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())
    val existingNote = notes.find { it.id == noteId }

    // 2. États des champs (initialisés avec les données de la note existante si elle existe)
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(NoteCategory.PERSONNEL) }

    // LaunchedEffect permet de remplir les champs une fois que la note est chargée
    LaunchedEffect(existingNote) {
        existingNote?.let {
            title = it.title
            content = it.content
            selectedCategory = it.category
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (noteId == -1) "Nouvelle Note" else "Modifier la Note") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Titre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Contenu") },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )

            Text("Catégorie")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                NoteCategory.entries.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(category.name) }
                    )
                }
            }

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        if (noteId == -1) {
                            // MODE CRÉATION
                            viewModel.addNote(Note(title = title, content = content, category = selectedCategory))
                        } else {
                            // MODE MODIFICATION
                            existingNote?.let {
                                viewModel.updateNote(it.copy(title = title, content = content, category = selectedCategory))
                            }
                        }
                        onSaveSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (noteId == -1) "Enregistrer" else "Mettre à jour")
            }
        }
    }
}