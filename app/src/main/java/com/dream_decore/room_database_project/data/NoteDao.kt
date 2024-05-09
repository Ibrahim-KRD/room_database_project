package com.dream_decore.room_database_project.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dream_decore.room_database_project.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    fun Insert(note: Note)

    @Delete
    fun Delete(note: Note)

    @Query("SELECT * FROM Note")
    fun GetAllNotes(): Flow<List<Note>>
}