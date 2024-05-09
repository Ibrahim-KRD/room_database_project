package com.dream_decore.room_database_project.presentation

import androidx.lifecycle.ViewModel
import com.dream_decore.room_database_project.data.NoteDao
import com.dream_decore.room_database_project.model.Note
import kotlinx.coroutines.flow.Flow

class NoteViewModel(
    val noteDao: NoteDao,
) : ViewModel() {

//region dummy data
    fun addData() {
        for (i in 0..10) {
            val note = Note(
                title = "note ${(0..10).random()}",
                description = "this is the  ${(0..10).random()}th description for the above note title "
            )
            noteDao.Insert(note)
        }
    }
//endregion

    fun addNote(note: Note) {
        noteDao.Insert(note)
    }

    fun getAllNote(): Flow<List<Note>> {
        return noteDao.GetAllNotes()
    }

    fun DeleteNote(note: Note) {
        noteDao.Delete(note)
    }

}