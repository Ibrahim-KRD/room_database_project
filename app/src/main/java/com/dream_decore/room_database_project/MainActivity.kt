package com.dream_decore.room_database_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.dream_decore.room_database_project.data.NoteDatabase
import com.dream_decore.room_database_project.presentation.NoteEditUIComponent
import com.dream_decore.room_database_project.presentation.NoteList
import com.dream_decore.room_database_project.presentation.NoteViewModel
import com.dream_decore.room_database_project.ui.theme.Room_database_projectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db by lazy {
            Room.databaseBuilder(
                context = applicationContext,
                klass = NoteDatabase::class.java,
                name = "noteDatabase.db",

                ).allowMainThreadQueries().build()
        }

        val noteVM = viewModels<NoteViewModel>(
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return NoteViewModel(db.noteDao) as T
                    }
                }
            }
        )

        setContent {
            Room_database_projectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    Column(

                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        NoteEditUIComponent(
                            addNote = { note ->
                            noteVM.value.addNote(note)
                        })
                        NoteList(noteVM)
                    }
                }
            }
        }
    }
}
