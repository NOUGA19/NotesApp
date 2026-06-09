package com.example.notesapp.data.database

import androidx.room.*
import com.example.notesapp.data.model.Note
import com.example.notesapp.data.model.NoteCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // 1. Lire toutes les notes (triées par les plus récentes d'abord)
    @Query("SELECT * FROM notes_table ORDER BY isPinned DESC, createdAt DESC")
    fun getAllNotes(): Flow<List<Note>>

    // 2. Insérer une nouvelle note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    // 3. Modifier une note existante
    @Update
    suspend fun updateNote(note: Note)

    // 4. Supprimer une note
    @Delete
    suspend fun deleteNote(note: Note)

    // 5. Chercher une note par son titre
    @Query("SELECT * FROM notes_table WHERE title LIKE :searchQuery")
    fun searchNotes(searchQuery: String): Flow<List<Note>>

    @Query("SELECT * FROM notes_table WHERE category = :category ORDER BY createdAt DESC")
    fun getNotesByCategory(category: NoteCategory): Flow<List<Note>>



}