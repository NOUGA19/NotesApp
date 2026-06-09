package com.example.notesapp.data.repository

import com.example.notesapp.data.database.NoteDao
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.model.NoteCategory
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun delete(note: Note) {
        noteDao.deleteNote(note)
    }

    fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query)
    }

    fun getNotesByCategory(category: NoteCategory): Flow<List<Note>> {
        return noteDao.getNotesByCategory(category)
    }
}