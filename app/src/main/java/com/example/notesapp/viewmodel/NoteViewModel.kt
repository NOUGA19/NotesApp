package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.model.NoteCategory
import com.example.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    // 1. On récupère toutes les notes du Repository sous forme de flux (Flow)
    val allNotes: Flow<List<Note>> = repository.allNotes

    // 2. Fonction pour ajouter une note
    // On utilise "viewModelScope.launch" pour que ça tourne en arrière-plan sans bloquer l'app
    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }

    // 3. Fonction pour modifier une note
    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    // 4. Fonction pour supprimer une note
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }

    // 5. Fonction pour rechercher des notes
    fun searchNotes(query: String): Flow<List<Note>> {
        return repository.searchNotes("%$query%") // Les % servent à chercher "autour" du mot
    }

    fun getNotesByCategory(category: NoteCategory): Flow<List<Note>> {
        return repository.getNotesByCategory(category)
    }

    fun togglePin(note: Note) {
        viewModelScope.launch {
            val updatedNote = note.copy(isPinned = !note.isPinned)
            repository.update(updatedNote)
        }
    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Classe ViewModel inconnue")
    }
}