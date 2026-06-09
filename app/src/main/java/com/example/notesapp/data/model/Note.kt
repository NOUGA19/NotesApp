package com.example.notesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val content: String,
    val category: NoteCategory,
    val createdAt: Long = System.currentTimeMillis(),
    val isPinned: Boolean = false
)