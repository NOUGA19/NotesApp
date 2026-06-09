package com.example.notesapp.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.notesapp.data.model.NoteCategory

// Palette Bleue Moderne
val BluePrimary = Color(0xFF2196F3)
val BlueSecondary = Color(0xFF03A9F4)
val BlueTertiary = Color(0xFF3F51B5)

// Couleurs pour les catégories (pour les badges)
val CourseColor = Color(0xFFFF9800)    // Orange
val WorkColor = Color(0xFF4CAF50)      // Vert
val PersonalColor = Color(0xFF9C27B0)  // Violet

fun NoteCategory.toColor(): Color {
    return when (this) {
        NoteCategory.COURS -> CourseColor   // Orange
        NoteCategory.TRAVAIL -> WorkColor    // Vert
        NoteCategory.PERSONNEL -> PersonalColor // Violet
    }
}