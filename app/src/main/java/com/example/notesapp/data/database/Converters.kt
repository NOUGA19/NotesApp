package com.example.notesapp.data.database

import androidx.room.TypeConverter
import com.example.notesapp.data.model.NoteCategory

class Converters {
    @TypeConverter
    fun fromCategory(category: NoteCategory): String {
        return category.name
    }

    @TypeConverter
    fun toCategory(value: String): NoteCategory {
        return NoteCategory.valueOf(value)
    }
}