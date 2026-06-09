package com.example.notesapp.ui.screens.list

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.model.NoteCategory
import com.example.notesapp.ui.components.NoteItem
import com.example.notesapp.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    viewModel: NoteViewModel,
    onNoteClick: (Int) -> Unit,
    onAddNoteClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    // 1. État pour la catégorie sélectionnée (null = "Toutes")
    var selectedCategory by remember { mutableStateOf<NoteCategory?>(null) }

    // 2. Logique de filtrage combinée
    val notesFlow = when {
        searchQuery.isNotEmpty() -> viewModel.searchNotes(searchQuery)
        selectedCategory != null -> viewModel.getNotesByCategory(selectedCategory!!)
        else -> viewModel.allNotes
    }

    val notes by notesFlow.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mes Notes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNoteClick) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            // Barre de Recherche
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                placeholder = { Text("Rechercher...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = MaterialTheme.shapes.medium
            )

            // 3. Barre de Filtres (Catégories)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()) // Permet de slider si c'est trop large
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Bouton "Toutes"
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                    label = { Text("Toutes") }
                )

                // Boutons pour chaque catégorie
                NoteCategory.entries.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(category.name) }
                    )
                }
            }

            // Liste des notes
            if (notes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("Aucune note trouvée")
                }
            } else {
                LazyColumn(contentPadding = PaddingValues(8.dp)) {
                    items(notes) { note ->
                        NoteItem(
                            note = note,
                            onClick = { onNoteClick(note.id) },
                            onLongClick = { viewModel.togglePin(note) } // C'est ici !
                        )
                    }
                }
            }
        }
    }
}