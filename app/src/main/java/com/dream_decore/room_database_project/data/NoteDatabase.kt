package com.dream_decore.room_database_project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dream_decore.room_database_project.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase :RoomDatabase() {
    abstract val noteDao:NoteDao
}