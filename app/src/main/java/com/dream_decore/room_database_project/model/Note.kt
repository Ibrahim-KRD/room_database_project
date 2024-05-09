package com.dream_decore.room_database_project.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val title: String,
    val description: String,
)