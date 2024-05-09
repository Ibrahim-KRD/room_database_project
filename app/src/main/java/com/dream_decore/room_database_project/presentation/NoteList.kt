package com.dream_decore.room_database_project.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dream_decore.room_database_project.model.Note

@Composable
fun NoteList(noteVM: Lazy<NoteViewModel>) {

    val noteList = noteVM.value.getAllNote().collectAsState(initial = emptyList())

    LazyColumn {
        if (noteList.value.isEmpty()) item {
            Text(text = "Click Add Note to insert new notes")
        }
        else
            items(noteList.value) { item ->
               Column {
                   ListItem(
                       headlineContent = {
                           Text(text = item.title, style = MaterialTheme.typography.headlineSmall)
                       },
                       supportingContent = {
                           Text(text = item.description)
                       },
                       trailingContent = {
                           IconButton(onClick = { noteVM.value.DeleteNote(item) }) {
                               Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                           }
                       }, leadingContent = {
                           Icon(imageVector = Icons.Default.Done, contentDescription =null )
                       }
                   )
                   Divider()
               }

            }
    }

}


@Composable
fun NoteEditUIComponent(addNote: (note: Note) -> Unit) {
    var noteTitle by remember {
        mutableStateOf("")
    }

    var noteDescription by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New Note", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = noteTitle, onValueChange = { noteTitle = it }, label = {
            Text(text = "note title")
        })
        OutlinedTextField(value = noteDescription,
            onValueChange = { noteDescription = it },
            modifier = Modifier.height(100.dp),
            label = { Text(text = "note description") })
        Button(onClick = {
            if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty())
                addNote.invoke(
                    Note(
                        title = noteTitle,
                        description = noteDescription
                    )
                )
            else
                Toast.makeText(context, "please add note information", Toast.LENGTH_LONG).show()
        }, content = {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text(text = "Add Note")
        })
    }

}


