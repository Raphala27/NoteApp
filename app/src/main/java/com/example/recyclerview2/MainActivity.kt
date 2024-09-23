package com.example.recyclerview2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview2.models.Note
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteDao: NoteDao
    private lateinit var noteDatabase: NoteDatabase

    private var notes = mutableListOf<Note>()

    // Code pour identifier une modification
    private val EDIT_NOTE_REQUEST = 1
    private val ADD_NOTE_REQUEST = 2 // Pour l'ajout de notes
    private var currentNotePosition: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialiser la base de données
        noteDatabase = NoteDatabase.getDatabase(this)
        noteDao = noteDatabase.noteDao()

        // Récupérer les notes de la base de données Room
        lifecycleScope.launch {
            notes = noteDao.getAllNotes().toMutableList()

            // Initialiser l'adaptateur avec les actions d'édition et de suppression
            noteAdapter = NoteAdapter(notes,
                editAction = { note, position ->
                    currentNotePosition = position
                    val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
                    intent.putExtra("note_title", note.title)
                    intent.putExtra("note_description", note.description)
                    startActivityForResult(intent, EDIT_NOTE_REQUEST)
                },
                deleteAction = { note, position ->
                    lifecycleScope.launch {
                        noteDao.delete(note)  // Supprimer la note de la base de données
                        notes.removeAt(position)
                        noteAdapter.notifyItemRemoved(position)
                    }
                }
            )

            // Assigner l'adaptateur au RecyclerView
            recyclerView.adapter = noteAdapter
        }

        // Bouton pour ajouter une nouvelle note
        val addNoteButton: Button = findViewById(R.id.addNoteButton)
        addNoteButton.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_NOTE_REQUEST) {
                val newTitle = data?.getStringExtra("new_note_title")
                val newDescription = data?.getStringExtra("new_note_description")

                if (newTitle != null && newDescription != null) {
                    val newNote = Note(title = newTitle, description = newDescription)

                    // Insérer la note dans la base de données
                    lifecycleScope.launch {
                        noteDao.insert(newNote)  // Sauvegarder dans Room
                        notes = noteDao.getAllNotes().toMutableList() // Mettre à jour la liste locale
                        noteAdapter.updateNotes(notes)
                    }
                }
            } else if (requestCode == EDIT_NOTE_REQUEST) {
                val newTitle = data?.getStringExtra("new_note_title")
                val newDescription = data?.getStringExtra("new_note_description")

                if (newTitle != null && newDescription != null && currentNotePosition != -1) {
                    val note = notes[currentNotePosition]
                    note.title = newTitle
                    note.description = newDescription

                    // Mettre à jour la base de données
                    lifecycleScope.launch {
                        noteDao.update(note)  // Mise à jour dans Room
                        notes = noteDao.getAllNotes().toMutableList()  // Mettre à jour la liste locale
                        noteAdapter.updateNotes(notes)
                    }
                }
            }
        }
    }






}